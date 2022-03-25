package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private BrandService brandService;



    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateBrandRequest createBrandRequest) {
        this.brandService.add(createBrandRequest);

    }

    @GetMapping("/getall")
    public List<ListBrandDto> getAll() {
        return brandService.getAll();
    }


}
