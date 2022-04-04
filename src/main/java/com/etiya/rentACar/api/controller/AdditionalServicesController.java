package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.responses.additionalService.AdditionalServiceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesController {

   private AdditionalServiceService additionalService;

    public AdditionalServicesController(AdditionalServiceService additionalService) {
        this.additionalService = additionalService;
    }

    @GetMapping("/getall")
    public DataResult<List<AdditionalServiceDto>> getAll(){
        return additionalService.getAll();
    }
}
