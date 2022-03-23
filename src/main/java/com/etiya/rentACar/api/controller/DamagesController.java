package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.responses.damageReponses.ListDamageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public List<ListDamageDto> getById(int id) {
        return this.damageService.getByCarId(id);
    }

}
