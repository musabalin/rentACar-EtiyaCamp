package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private CityService cityService;

    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public Result add(CreateCityRequest createCityRequest) {
        return cityService.add(createCityRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(DeleteCityRequest deleteCityRequest) {
        return cityService.delete(deleteCityRequest);
    }

    @PutMapping("/update")
    public Result delete(UpdateCityRequest updateCityRequest) {
        return cityService.update(updateCityRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListCityDto>> getAll() {
        return cityService.getAll();
    }


}
