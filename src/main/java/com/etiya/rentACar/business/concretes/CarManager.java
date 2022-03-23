package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.CarDao;
import com.etiya.rentACar.entities.concretes.Brand;
import com.etiya.rentACar.entities.concretes.Car;
import com.etiya.rentACar.entities.concretes.Color;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {
    private CarDao carDao;
    private ModelMapperService modelMapperService;

    public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public void add(CreateCarRequest createCarRequest) {


        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);

    }

    @Override
    public List<ListCarDto> getAll() {

        List<Car> cars = carDao.findAll();
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return response;
    }
}

