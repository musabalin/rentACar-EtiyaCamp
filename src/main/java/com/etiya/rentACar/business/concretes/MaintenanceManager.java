package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.MaintenanceDao;
import com.etiya.rentACar.entities.concretes.Durum;
import com.etiya.rentACar.entities.concretes.Maintenance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceManager implements MaintenanceService {

    MaintenanceDao maintenanceDao;
    ModelMapperService modelMapperService;

    public MaintenanceManager(MaintenanceDao maintenanceDao, ModelMapperService modelMapperService) {
        this.maintenanceDao = maintenanceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(CreateMaintenanceRequest createMaintenanceRequest, Durum durum) {

        checkIfCarId(createMaintenanceRequest.getCarId(), durum);
        Maintenance maintenance = modelMapperService.forRequest()
                .map(createMaintenanceRequest, Maintenance.class);
        maintenanceDao.save(maintenance);
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
    public List<ListMaintenanceDto> getAllDurum(Durum durum) {
        List<Maintenance> maintenances = this.maintenanceDao.findAll();
        List<ListMaintenanceDto> response = maintenances.stream()
                .filter(lista -> lista.getDurum() == durum)
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
    public void update(UpdateMaintenanceRequest updateMaintenanceRequest, Durum durum) {

        List<Maintenance> maintenance1 =
                maintenanceDao.getByMaintenanceId(updateMaintenanceRequest.getMaintenanceId());


        Maintenance maintenance = modelMapperService.forRequest()
                .map(maintenance1, Maintenance.class);
        maintenance.setDurum(durum);
        maintenanceDao.save(maintenance);

    }


    private void checkIfCarId(int car_id, Durum durum) {
        if (maintenanceDao.existsMaintenanceByCarId(car_id) && durum == Durum.Available) {

            throw new RuntimeException("Bu araç bakımda");
        }
    }


}
