package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.carRequests.*;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.concretes.CarStates;

import java.util.List;

public interface CarService {

    Result add(CreateCarRequest createCarRequest);

    Result update(UpdateCarRequest carRequest);

    Result delete(DeleteCarRequest carRequest);

    void updateCarStatus(UpdateStatusRequest updateStatusRequest);

    Result updateCity(UpdateCarCityRequest updateCarCityRequest);

    CarDto getById(int carId);

    List<ListCarDto> getAllStatus(CarStates carStates);

    DataResult<List<ListCarDto>> getAll();


    DataResult<List<ListCarDto>> getByCityId(int cityId);


    DataResult<List<ListCarDto>> getByModelYear(short modelYear);

    DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<ListCarDto>> getAllSorted();

    // ListCarDto getByCarId(int id);
}
