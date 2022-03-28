package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;

import java.util.List;

public interface MaintenanceService {

    void add(CreateMaintenanceRequest createMaintenanceRequest);

    List<ListMaintenanceDto> getAll();

    List<ListMaintenanceDto> getByCarId(int id);

    void update(UpdateMaintenanceRequest createMaintenanceRequest);

}
