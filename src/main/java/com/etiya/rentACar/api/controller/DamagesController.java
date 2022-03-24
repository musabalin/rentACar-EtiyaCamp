package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.responses.colorResponse.ListColorDto;
import com.etiya.rentACar.business.responses.damageReponses.ListDamageDto;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/api/damages")
public class DamagesController {

    DamageService damageService;

    public DamagesController(DamageService damageService) {
        this.damageService = damageService;
    }

    @PostMapping("/add")
    public void add(CreateDamageRequest createDamageRequest) {
        damageService.add(createDamageRequest);
    }

    @GetMapping("/getall")
    public List<ListDamageDto> getAll() {
        return this.damageService.getAll();
    }

    @GetMapping("getbycarid/{id}")
    public List<ListDamageDto> getCarById(int id) {
        return this.damageService.getByCarId(id);
    }

    @GetMapping("/getallpaged")
    public List<ListDamageDto> getAllPaged(@RequestParam int pageNo,@RequestParam int pageSize) {
        return damageService.getAllPaged(pageNo, pageSize);
    }

    @GetMapping("/getallsorted")
    public List<ListDamageDto> getAllSorted(@RequestParam String optinal, @RequestParam String field){
        return damageService.getAllSorted(optinal,field);
    }


}
