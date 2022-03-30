package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface MaintenanceService {

    Result add(CreateMaintenanceRequest createMaintenanceRequest);

    Result update(UpdateMaintenanceRequest updateMaintenanceRequest);

    Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);

    DataResult<List<ListMaintenanceDto>> getAll();

    DataResult<List<ListMaintenanceDto>> getByCarId(int id);


}
