package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.entities.concretes.Durum;
import com.etiya.rentACar.entities.concretes.Maintenance;

import java.util.List;

public interface MaintenanceService {

    void add(CreateMaintenanceRequest createMaintenanceRequest,Durum durum);

    List<ListMaintenanceDto> getAll();

    List<ListMaintenanceDto> getAllDurum(Durum durum);

    List<ListMaintenanceDto> getByCarId(int id);

    void update(UpdateMaintenanceRequest updateMaintenanceRequest, Durum durum);

}
