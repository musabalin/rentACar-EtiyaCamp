package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.concretes.CarStates;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarRequest carRequest) {
        return carService.add(carRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
        return carService.delete(deleteCarRequest);
    }

    @GetMapping("getall")
    public DataResult<List<ListCarDto>> getAll() {
        return carService.getAll();
    }

    @GetMapping("/getallstatus")
    List<ListCarDto> getAllStatus(@RequestParam CarStates carStates) {
        return this.carService.getAllStatus(carStates);
    }


    @GetMapping("/getbymodelyear")
    // @GetMapping("/getmodelyear/{id}")
    public DataResult<List<ListCarDto>> getByModelYear(@RequestParam("modelYear") short modelYear) {
        //  public List<ListCarDto> getByModelYear ( short modelYear){
        return carService.getByModelYear(modelYear);
    }

    @GetMapping("/getbycityid")
    public DataResult<List<ListCarDto>> getByCityId(@RequestParam("cityId") int id) {

        return carService.getByCityId(id);
    }


    @PutMapping("/update")
    public Result Update(@RequestBody UpdateCarRequest updateCarRequest) {
        return carService.update(updateCarRequest);
    }


    @GetMapping("/getallpaged")
    public DataResult<List<ListCarDto>> getAllPaged(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return carService.getAllPaged(pageNo, pageSize);
    }

    @GetMapping("/getallsorted")
    public DataResult<List<ListCarDto>> getAllSorted() {
        return this.carService.getAllSorted();
    }


}
