package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarCityRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateKilometerRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateStatusRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeliveryCarRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.utilities.modelMapperService.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.RentalDao;
import com.etiya.rentACar.entities.concretes.CarStates;
import com.etiya.rentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalManager implements RentalService {

    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    public RentalManager(RentalDao rentalDao,
                         ModelMapperService modelMapperService,
                         CarService carService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    //Ara?? stat?? g??ncelleme
    private void updateCarState(int carId, CarStates carStates) {

        UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();
        updateStatusRequest.setId(carId);
        updateStatusRequest.setStatusName(carStates);
        carService.updateCarStatus(updateStatusRequest);
    }

    //Ara?? kilometre g??ncellenmesi
    private void updateCarKilometre(int carId, int kilometer) {
        UpdateKilometerRequest updateKilometerRequest = new UpdateKilometerRequest();
        updateKilometerRequest.setCarId(carId);
        updateKilometerRequest.setKilometer(kilometer);
        carService.updateKilometer(updateKilometerRequest);
    }

    //Ara?? g??ncel ??ehir
    private void UpdateCarCity(int carId, int cityId) {
        UpdateCarCityRequest updateCarCityRequest = new UpdateCarCityRequest();
        updateCarCityRequest.setCarId(carId);
        updateCarCityRequest.setCityId(cityId);
        carService.updateCity(updateCarCityRequest);
    }


    @Override
    public DataResult<Rental> add(CreateRentalRequest createRentalRequest) {

        checkCarStatus(createRentalRequest.getCarId());

        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);

        CarDto car = carService.getById(createRentalRequest.getCarId());

        rental.setBeforeRentalKilometer(car.getKilometer());

        rentalDao.save(rental);
        //??ehir G??ncelleme
        UpdateCarCity(createRentalRequest.getCarId(), createRentalRequest.getReturnCityId());
        //Stat?? g??ncelleme
        updateCarState(createRentalRequest.getCarId(), CarStates.Rented);

        return new SuccessDataResult<>(rental, BusinessMessages.RentalMessages.RENTAL_RENTED);
    }

    private void checkCarStatus(int id) {
        CarDto car = carService.getById(id);
        if (car.getStatus() != CarStates.Available) {
            throw new RuntimeException("Ara?? m??sait de??il.");
        }
    }

    @Override
    public Result deliveryCar(DeliveryCarRequest deliveryCarRequest) {

        //kilometre ve tarih g??ncellemesi
        Rental result = rentalDao.getById(deliveryCarRequest.getId());
        result.setDateReturned(deliveryCarRequest.getReturnDate());
        result.setAfterRentalKilometer(deliveryCarRequest.getAfterRentKilometer());
        rentalDao.save(result);

        int carId = deliveryCarRequest.getCarId();
        //Kilometre g??ncellenmesi
        updateCarKilometre(carId, deliveryCarRequest.getAfterRentKilometer());
        //??ehir g??ncelleme
        UpdateCarCity(deliveryCarRequest.getCarId(), deliveryCarRequest.getReturnCityId());
        //Stat?? g??ncellemesi
        updateCarState(carId, CarStates.Available);

        return new SuccessResult("Ara?? teslim edildi.");
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
        return new SuccessDataResult<>(response);
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
