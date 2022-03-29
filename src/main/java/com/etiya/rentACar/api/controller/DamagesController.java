package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.damageRequests.CreateCarDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteCarDamageRequest;
import com.etiya.rentACar.business.responses.damageReponses.ListDamageDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/damages")
public class DamagesController {

    DamageService carDamageService;

    public DamagesController(DamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateCarDamageRequest createDamageRequest) {
        carDamageService.add(createDamageRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteCarDamageRequest carDamageRequest) {
        carDamageService.delete(carDamageRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateCarRequest updateCarRequest) {
        carDamageService.update(updateCarRequest);
    }

    @GetMapping("/getall")
    public List<ListDamageDto> getAll() {
        return this.carDamageService.getAll();
    }

    @GetMapping("getbycarid/{id}")
    public List<ListDamageDto> getCarById(int id) {
        return this.carDamageService.getByCarId(id);
    }

    @GetMapping("/getallpaged")
    public List<ListDamageDto> getAllPaged(@RequestParam int pageNo, @RequestParam int pageSize) {
        return carDamageService.getAllPaged(pageNo, pageSize);
    }

    @GetMapping("/getallsorted")
    public List<ListDamageDto> getAllSorted(@RequestParam String optinal, @RequestParam String field) {
        return carDamageService.getAllSorted(optinal, field);
    }


}
