package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.requests.carRequests.UpdateStatusRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeliveryCarRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.RentalDao;
import com.etiya.rentACar.entities.concretes.CarStates;
import com.etiya.rentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Objects;

@Service
public class RentalManager implements RentalService {

    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private AdditionalServiceService additionalService;

    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService, AdditionalServiceService additionalService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.additionalService = additionalService;
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {


        checkCarStatus(createRentalRequest.getCarId());
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rentalDao.save(rental);
        //Statü güncelleme
        UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();
        updateStatusRequest.setCityId(createRentalRequest.getRentCity());
        updateStatusRequest.setId(createRentalRequest.getCarId());
        updateStatusRequest.setStatusName(CarStates.Rented);
        carService.updateCarStatus(updateStatusRequest);

        return new SuccessResult();
    }

    private void checkCarStatus(int id) {
        CarDto car = carService.getById(id);
        if (car.getStatus() != CarStates.Available) {
            throw new RuntimeException("Araç müsait değil.");
        }
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        return null;
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        return null;
    }

    @Override
    public Result deliveryCar(DeliveryCarRequest deliveryCarRequest) {


        Rental rental = modelMapperService.forRequest().map(deliveryCarRequest, Rental.class);
        var car = carService.getById(deliveryCarRequest.getCarId());
        //
        UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();
        updateStatusRequest.setId(deliveryCarRequest.getCarId());
        updateStatusRequest.setStatusName(CarStates.Available);
        updateStatusRequest.setCityId(deliveryCarRequest.getReturnCity());
        carService.updateCarStatus(updateStatusRequest);
        //
        //Kiralama Günü hesaplama
        Period day = Period.between(rental.getDateAdded(), rental.getDateReturned());
        int daysCount = day.getDays();

        //Ek Servis ücretleri
        var additionalServiceDto = additionalService.getById(deliveryCarRequest.getAdditionalServiceId());

        //Toplam ücret hesaplama ve farklı şehirdeki aracın maliyet eklenmesi
        rental.setTotalPrice(car.getDailyPrice() * daysCount + additionalServiceDto.getPrice());
        if (!Objects.equals(deliveryCarRequest.getRentCity(), deliveryCarRequest.getReturnCity())) {
            rental.setTotalPrice(rental.getTotalPrice() + 750);
        }

        rentalDao.save(rental);
        return new SuccessResult();



    }
}
