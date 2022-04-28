package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.AdditionalServiceOrderService;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.DeleteAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.UpdateAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.responses.additionalServiceOrder.AdditionalServiceOrderDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class AdditionalServiceOrderController {

    private AdditionalServiceOrderService additionalServiceOrderService;

    public AdditionalServiceOrderController(AdditionalServiceOrderService additionalServiceOrderService) {
        this.additionalServiceOrderService = additionalServiceOrderService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateAdditionalServiceOrderRequest createAdditionalServiceOrderRequest) {
        return additionalServiceOrderService.add(createAdditionalServiceOrderRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteAdditionalServiceOrderRequest deleteAdditionalServiceOrderRequest) {
        return additionalServiceOrderService.delete(deleteAdditionalServiceOrderRequest);
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody UpdateAdditionalServiceOrderRequest updateAdditionalServiceOrderRequest) {
        return additionalServiceOrderService.update(updateAdditionalServiceOrderRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<AdditionalServiceOrderDto>> getAll() {
        return additionalServiceOrderService.getAll();
    }
}
