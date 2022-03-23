package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping("/add")
    public void add(@RequestBody CreateCarRequest carRequest) {
        carService.add(carRequest);
    }
    @GetMapping("getall")
    public List<ListCarDto> getAll(){
        return carService.getAll();
    }


}
