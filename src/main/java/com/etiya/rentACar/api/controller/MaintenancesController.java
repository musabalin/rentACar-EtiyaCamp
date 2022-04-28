package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {

    MaintenanceService maintenanceService;

    public MaintenancesController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
        return maintenanceService.add(createMaintenanceRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
        return maintenanceService.update(updateMaintenanceRequest);
    }
/*
    @GetMapping("/getByCarId")
    public void getByCarId(@RequestParam int id) {
        maintenanceService.getByCarId(id);
    }*/
    @GetMapping("/getbycarid/{id}")
    public DataResult<List<ListMaintenanceDto>> getCarById(int id) {
        return this.maintenanceService.getByCarId(id);
    }

    @GetMapping("/getall")
    public DataResult<List<ListMaintenanceDto>> getAll() {
        return maintenanceService.getAll();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteMaintenanceRequest deleteMaintenanceRequest) {
        maintenanceService.delete(deleteMaintenanceRequest);
    }


}
