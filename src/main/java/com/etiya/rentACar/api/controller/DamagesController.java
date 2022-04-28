package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageReponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
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
    public Result add(@RequestBody CreateDamageRequest createDamageRequest) {
        return
                this.carDamageService.add(createDamageRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteDamageRequest carDamageRequest) {
       return carDamageService.delete(carDamageRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateDamageRequest updateCarDamageRequest) {
        return carDamageService.update(updateCarDamageRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListDamageDto>> getAll() {
        return this.carDamageService.getAll();
    }

    @GetMapping("getbycarid/{id}")
    public DataResult<List<ListDamageDto>> getCarById(int id) {
        return this.carDamageService.getByCarId(id);
    }

    @GetMapping("/getallpaged")
    public DataResult<List<ListDamageDto>> getAllPaged(@RequestParam int pageNo, @RequestParam int pageSize) {
        return carDamageService.getAllPaged(pageNo, pageSize);
    }

    @GetMapping("/getallsorted")
    public DataResult<List<ListDamageDto>> getAllSorted(@RequestParam String optinal, @RequestParam String field) {
        return carDamageService.getAllSorted(optinal, field);
    }


}
