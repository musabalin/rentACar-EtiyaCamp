package com.etiya.rentACar.business.concretes;

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

@Service
public class RentalManager implements RentalService {

    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {


        checkCarStatus(createRentalRequest.getCarId());
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rentalDao.save(rental);
        UpdateStatusRequest updateStatusRequest=new UpdateStatusRequest();
        updateStatusRequest.setCarId(createRentalRequest.getCarId());
        updateStatusRequest.setStatusName(CarStates.Rented);
        carService.updateCarStatus(updateStatusRequest);
        return new SuccessResult();
    }

    private void checkCarStatus(int id){
        CarDto car=carService.getById(id);
        if (car.getStatus()!=CarStates.Available){
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

        Rental rental=modelMapperService.forRequest().map(deliveryCarRequest,Rental.class);

        UpdateStatusRequest updateStatusRequest=new UpdateStatusRequest();
        updateStatusRequest.setCarId(deliveryCarRequest.getCarId());
        updateStatusRequest.setStatusName(CarStates.Available);
        carService.updateCarStatus(updateStatusRequest);
        rentalDao.save(rental);
        return new SuccessResult();
    }
}
