package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintananceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintananceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.damageReponses.ListDamageDto;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.entities.concretes.Durum;
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
    public void add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest,@RequestParam Durum durum) {
        maintenanceService.add(createMaintenanceRequest,durum);
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest, @RequestParam Durum durum) {
        maintenanceService.update(updateMaintenanceRequest, durum);
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

    @GetMapping("/getalldurum")
    public List<ListMaintenanceDto> getAll(@RequestParam Durum durum) {
        return maintenanceService.getAllDurum(durum);
    }


}
