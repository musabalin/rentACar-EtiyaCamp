package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
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
    public void add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {

        maintenanceService.add(createMaintenanceRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
        maintenanceService.update(updateMaintenanceRequest);
    }
/*
    @GetMapping("/getByCarId")
    public void getByCarId(@RequestParam int id) {
        maintenanceService.getByCarId(id);
    }*/

    @GetMapping("/getbycarid/{id}")
    public List<ListMaintenanceDto> getCarById(int id) {
        return this.maintenanceService.getByCarId(id);
    }

    @GetMapping("/getall")
    public List<ListMaintenanceDto> getAll() {
        return maintenanceService.getAll();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteMaintenanceRequest deleteMaintenanceRequest) {
        maintenanceService.delete(deleteMaintenanceRequest);
    }


}
