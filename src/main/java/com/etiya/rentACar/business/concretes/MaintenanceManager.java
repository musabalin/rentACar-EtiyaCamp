package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.carRequests.UpdateStatusRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.MaintenanceDao;
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
    public void add(CreateMaintenanceRequest createMaintenanceRequest) {

        checkIfCarId(createMaintenanceRequest.getCarId());
        Maintenance maintenance = modelMapperService.forRequest()
                .map(createMaintenanceRequest, Maintenance.class);
        maintenanceDao.save(maintenance);

        UpdateStatusRequest updateStatusRequest = modelMapperService.forRequest().map(maintenance, UpdateStatusRequest.class);

        carService.updateMaintenanceStatus(updateStatusRequest.getCarId());


    }


    @Override
    public List<ListMaintenanceDto> getAll() {
        List<Maintenance> maintenances = this.maintenanceDao.findAll();
        List<ListMaintenanceDto> response = maintenances.stream()
                .map(maintenance -> modelMapperService.forDto().map(maintenance, ListMaintenanceDto.class))
                .collect(Collectors.toList());
        return response;
    }


    @Override
    public List<ListMaintenanceDto> getByCarId(int id) {
        List<Maintenance> maintenances = this.maintenanceDao.getByCarId(id);
        List<ListMaintenanceDto> response = maintenances.stream()
                .map(maintenance -> this.modelMapperService.forDto()
                        .map(maintenance, ListMaintenanceDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public void update(UpdateMaintenanceRequest updateMaintenanceRequest) {
        Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
        this.maintenanceDao.save(maintenance);

    }

    @Override
    public void delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
        this.maintenanceDao.deleteById(deleteMaintenanceRequest.getMaintenanceId());
    }


    private void checkIfCarId(int car_id) {
        if (maintenanceDao.existsMaintenanceByCarId(car_id)) {
            throw new RuntimeException("Bu araç bakımda");
        }
    }



}
