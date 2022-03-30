package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        return this.brandService.add(createBrandRequest);

    }

    @GetMapping("/getall")
    public DataResult<List<ListBrandDto>> getAll() {

        return brandService.getAll();
    }


}
