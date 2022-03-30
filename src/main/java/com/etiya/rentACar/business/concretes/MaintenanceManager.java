package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carRequests.UpdateStatusRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.crossCuttingConserns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.MaintenanceDao;
import com.etiya.rentACar.entities.concretes.CarStates;
import com.etiya.rentACar.entities.concretes.Maintenance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceManager implements MaintenanceService {

    MaintenanceDao maintenanceDao;
    ModelMapperService modelMapperService;
    CarService carService;

    public MaintenanceManager(MaintenanceDao maintenanceDao, ModelMapperService modelMapperService, CarService carService) {
        this.maintenanceDao = maintenanceDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public Result add(CreateMaintenanceRequest createMaintenanceRequest) {

        checkIfCarId(createMaintenanceRequest.getCarId());
        Maintenance maintenance = modelMapperService.forRequest()
                .map(createMaintenanceRequest, Maintenance.class);
        maintenanceDao.save(maintenance);

        UpdateStatusRequest updateStatusRequest = modelMapperService.forRequest().map(maintenance, UpdateStatusRequest.class);

        updateStatusRequest.setStatusName(CarStates.UnderMaintenance);
        carService.updateCarStatus(updateStatusRequest);
        return new SuccessResult();


    }


    @Override
    public DataResult<List<ListMaintenanceDto>> getAll() {
        List<Maintenance> maintenances = this.maintenanceDao.findAll();
        List<ListMaintenanceDto> response = maintenances.stream()
                .map(maintenance -> modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListMaintenanceDto>>(response);
    }


    @Override
    public DataResult<List<ListMaintenanceDto>> getByCarId(int id) {
        List<Maintenance> maintenances = this.maintenanceDao.getByCarId(id);
        List<ListMaintenanceDto> response = maintenances.stream()
                .map(maintenance -> this.modelMapperService.forDto()
                        .map(maintenance, ListMaintenanceDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListMaintenanceDto>>(response);
    }

    @Override
    public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
        Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
        this.maintenanceDao.save(maintenance);
        return new SuccessResult();
    }

    @Override
    public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
        this.maintenanceDao.deleteById(deleteMaintenanceRequest.getMaintenanceId());
        return new SuccessResult();
    }


    private void checkIfCarId(int car_id) {
        CarDto car1 = carService.getById(car_id);
        if (car1.getStatus() == CarStates.UnderMaintenance) {
            throw new BusinessException(BusinessMessages.MaintenanceMessages.CAR_UNDERMAINTENANCE);
        }
    }


}
