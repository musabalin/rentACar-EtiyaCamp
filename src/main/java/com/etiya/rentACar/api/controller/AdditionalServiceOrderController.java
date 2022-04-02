package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.AdditionalServiceOrderService;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class AdditionalServiceOrderController {

    private AdditionalServiceOrderService additionalServiceOrderService;

    public AdditionalServiceOrderController(AdditionalServiceOrderService additionalServiceOrderService) {
        this.additionalServiceOrderService = additionalServiceOrderService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateAdditionalServiceOrderRequest createAdditionalServiceOrderRequest){
        return additionalServiceOrderService.add(createAdditionalServiceOrderRequest);
    }
}
