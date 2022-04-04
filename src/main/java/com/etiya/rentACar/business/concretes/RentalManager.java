package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalServiceOrderService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarCityRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateStatusRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeliveryCarRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.RentalDao;
import com.etiya.rentACar.entities.concretes.CarStates;
import com.etiya.rentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RentalManager implements RentalService {

    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private AdditionalServiceOrderService additionalServiceOrderService;

    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService, AdditionalServiceOrderService additionalServiceOrderService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.additionalServiceOrderService = additionalServiceOrderService;
    }

    //Araç statü güncelleme
    private void updateCarState(int carId) {

        UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();
        updateStatusRequest.setId(carId);
        updateStatusRequest.setStatusName(CarStates.Rented);
        carService.updateCarStatus(updateStatusRequest);
    }

    //Araç güncel şehir
    private void UpdateCarCity(int carId, int cityId) {
        UpdateCarCityRequest updateCarCityRequest = new UpdateCarCityRequest();
        updateCarCityRequest.setCarId(carId);
        updateCarCityRequest.setCityId(cityId);
        carService.updateCity(updateCarCityRequest);
    }

    //Ek servislerin eklenmesi
    private void addAdditionalServices(List<Integer> additionalServices, int rentalId) {
        var additionalServiceOrder = new CreateAdditionalServiceOrderRequest();
        for (int additionalServiceId : additionalServices) {
            additionalServiceOrder.setAdditionalServiceId(additionalServiceId);
            additionalServiceOrder.setRentalId(rentalId);
            additionalServiceOrderService.add(additionalServiceOrder);
        }
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {


        checkCarStatus(createRentalRequest.getCarId());
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rentalDao.save(rental);

        /*
        updateState(rental.getId());
        UpdateStatusRequest updateStatusRequest = modelMapperService.forRequest().map(rental, UpdateStatusRequest.class);
        updateStatusRequest.setCityId(createRentalRequest.getRentCityId());
        updateStatusRequest.setId(createRentalRequest.getCarId());
        updateStatusRequest.setStatusName(CarStates.Rented);
        carService.updateCarStatus(updateStatusRequest);*/

        //Ek hizmetlerin eklenmesi
        addAdditionalServices(createRentalRequest.getAdditionalService(), rental.getId());
        //Şehir Güncelleme
        UpdateCarCity(createRentalRequest.getCarId(), createRentalRequest.getReturnCityId());
        //Statü güncelleme
        updateCarState(createRentalRequest.getCarId());
        //Şehir Kontrolü ve ekstra ücret ilavesi

        /*//Eski kodlar
        var additionalServiceOrder = new CreateAdditionalServiceOrderRequest();
        int rentalId = rental.getId();
        for (int additionalServiceId : createRentalRequest.getAdditionalService()) {
            additionalServiceOrder.setAdditionalServiceId(additionalServiceId);
            additionalServiceOrder.setRentalId(rentalId);
            additionalServiceOrderService.add(additionalServiceOrder);
        }*/

        return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_RENTED);
    }

    private void checkCarStatus(int id) {
        CarDto car = carService.getById(id);
        if (car.getStatus() != CarStates.Available) {
            throw new RuntimeException("Araç müsait değil.");
        }
    }

    @Override
    public Result deliveryCar(DeliveryCarRequest deliveryCarRequest) {
        //***Soru Her operasyon için ayrı kontrol(checkif) mi yazmak gerekir.

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
        // var additionalServiceDto = additionalService.getById(deliveryCarRequest.getAdditionalServiceId());

        //Toplam ücret hesaplama ve farklı şehirdeki aracın maliyet eklenmesi
        rental.setDailyPrice(car.getDailyPrice() * daysCount /*+ additionalServiceDto.getPrice()*/);
        if (!Objects.equals(deliveryCarRequest.getRentCity(), deliveryCarRequest.getReturnCity())) {
            rental.setDailyPrice(rental.getDailyPrice() + 750);
        }

        rentalDao.save(rental);
        return new SuccessResult();
    }

    @Override
    public RentalDto getById(int rentalId) {
        Rental rental = this.rentalDao.getById(rentalId);
        RentalDto rentalDto = this.modelMapperService.forDto().map(rental, RentalDto.class);
        return rentalDto;
    }

    @Override
    public DataResult<List<ListRentalDto>> getAll() {

        List<Rental> result = rentalDao.findAll();
        List<ListRentalDto> response = result.stream()
                .map(rental -> modelMapperService.forDto().map(rental, ListRentalDto.class))
                .collect(Collectors.toList());


        return new SuccessDataResult<List<ListRentalDto>>(response);
    }


    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        return null;
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        return null;
    }


}
