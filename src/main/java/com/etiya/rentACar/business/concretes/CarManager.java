package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carRequests.*;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.crossCuttingConserns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.modelMapperService.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
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
    public Result add(CreateCarRequest createCarRequest) {
        if (createCarRequest.getDailyPrice() < 50) {
            throw new BusinessException("Fiyat 50 TL'den küçük olamaz");
        }

        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult(BusinessMessages.Car.CAR_ADD);

    }

    @Override
    public Result update(UpdateCarRequest carRequest) {


        Car car = this.modelMapperService.forRequest().map(carRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult(BusinessMessages.Car.CAR_UPDATE);

    }

    @Override
    public Result delete(DeleteCarRequest carRequest) {
        this.carDao.deleteById(carRequest.getId());
        return new SuccessResult(BusinessMessages.Car.CAR_REMOVE);
    }

    @Override
    public void updateCarStatus(UpdateStatusRequest updateStatusRequest) {

        int carId = updateStatusRequest.getId();
        Car car = carDao.getById(carId);
        car.setStatus(updateStatusRequest.getStatusName());
        carDao.save(car);
    }

    @Override
    public Result updateCity(UpdateCarCityRequest updateCarCityRequest) {
        int carId = updateCarCityRequest.getCarId();
        Car car = carDao.getById(carId);
        UpdateCarRequest response = modelMapperService.forRequest().map(car, UpdateCarRequest.class);
        response.setCityId(updateCarCityRequest.getCityId());
        Car car1 = modelMapperService.forRequest().map(response, Car.class);
        carDao.save(car1);
        return new SuccessResult();
    }

    @Override
    public void updateKilometer(UpdateKilometerRequest updateKilometerRequest) {
        int carId = updateKilometerRequest.getCarId();
        Car car = carDao.getById(carId);
        UpdateCarRequest response = modelMapperService.forRequest().map(car, UpdateCarRequest.class);
        response.setKilometer(updateKilometerRequest.getKilometer());
        Car car1 = modelMapperService.forRequest().map(response, Car.class);
        carDao.save(car1);
    }


    @Override
    public CarDto getById(int carId) {
        Car result = this.carDao.getById(carId);
        CarDto response = modelMapperService.forRequest().map(result, CarDto.class);
        return response;
    }

    @Override
    public List<ListCarDto> getAllStatus(CarStates carStates) {
        List<Car> cars = carDao.findAll();
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .filter(x -> x.getStatusName() == carStates)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public DataResult<List<ListCarDto>> getAll() {
        List<Car> cars = carDao.findAll();
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(response);
    }

    @Override
    public DataResult<List<ListCarDto>> getByCityId(int cityId) {
        List<Car> cars = carDao.getByCityId(cityId);
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(response);
    }


    @Override
    public DataResult<List<ListCarDto>> getByModelYear(short modelYear) {
        List<Car> cars = carDao.getByModelYear(modelYear);
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(response);
    }

    @Override
    public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);//domain
        List<Car> cars = this.carDao.findAll(pageable).getContent();
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(response);
    }

    @Override
    public DataResult<List<ListCarDto>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC, "modelYear");
        List<Car> cars = this.carDao.findAll(sort);
        List<ListCarDto> response = cars.stream()
                .map(car -> modelMapperService.forDto().map(car, ListCarDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(response);
    }

//    @Override
//    public ListCarDto getByCarId(int id) {
//        Car car = carDao.getById(id);
//        ListCarDto car1 = this.modelMapperService.forDto().map(car, ListCarDto.class);
//        return car1;
//    }
}

