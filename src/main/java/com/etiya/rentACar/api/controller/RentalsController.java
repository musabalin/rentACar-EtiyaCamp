package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeliveryCarRequest;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

    private RentalService rentalService;

    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateRentalRequest createRentalRequest) {
        return rentalService.add(createRentalRequest);
    }

    @PostMapping("/deliveryCar")
    public Result deliveryCar(@RequestBody DeliveryCarRequest deliveryCarRequest) {
        return rentalService.deliveryCar(deliveryCarRequest);
    }

    @GetMapping(name = "getall")
    public DataResult<List<ListRentalDto>> getAll() {
        return rentalService.getAll();
    }
}
