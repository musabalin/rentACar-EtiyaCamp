package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.crossCuttingConserns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.CarDao;
import com.etiya.rentACar.entities.concretes.Car;
import com.etiya.rentACar.entities.concretes.CarStates;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        if (createCarRequest.getDailyPrice() < 50) {
            throw new BusinessException("Fiyat 50 TL'den küçük olamaz");
        }

        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);

    }

    @Override
    public void update(UpdateCarRequest carRequest) {


        Car car = this.modelMapperService.forRequest().map(carRequest, Car.class);
        this.carDao.save(car);


        /*
        Car car = carDao.getById(id);
        Car car1 = modelMapperService.forDto().map(carRequest, Car.class);
        car.setModelYear(car1.getModelYear());
        car.setColor(car1.getColor());
        car.setBrand(car1.getBrand());
        car.setDescription(car1.getDescription());
        car.setDailyPrice(car1.getDailyPrice());
        car.setCarState(car1.getCarState());
        carDao.save(car);*/
    }

    @Override
    public void delete(DeleteCarRequest carRequest) {
        this.carDao.deleteById(carRequest.getCarId());
    }

    @Override
    public void updateMaintenanceStatus(int id) {
        Car car = carDao.getById(id);
        car.setStatus(CarStates.UnderMaintenance);
        carDao.save(car);
    }

    @Override
    public CarDto getById(int id) {
        Car result = this.carDao.getById(id);
        CarDto response = modelMapperService.forRequest().map(result, CarDto.class);
        return response;
    }

    @Override
    public List<ListCarDto> getAllStatus(CarStates carStates) {
        List<Car> cars = carDao.findAll();
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .filter(x -> x.getStatus() == carStates)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListCarDto> getAll() {

        List<Car> cars = carDao.findAll();
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListCarDto> getByModelYear(short modelYear) {
        List<Car> cars = carDao.getByModelYear(modelYear);
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListCarDto> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);//domain
        List<Car> cars = this.carDao.findAll(pageable).getContent();
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListCarDto> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC, "modelYear");
        List<Car> cars = this.carDao.findAll(sort);
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public ListCarDto getByCarId(int id) {
        Car car = carDao.getById(id);
        ListCarDto car1 = this.modelMapperService.forDto().map(car, ListCarDto.class);
        return car1;
    }
}

