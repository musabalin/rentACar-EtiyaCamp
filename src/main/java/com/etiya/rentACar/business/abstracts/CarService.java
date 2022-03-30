package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
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

    Result updateMaintenanceStatus(int id);

    CarDto getById(int id);

    List<ListCarDto> getAllStatus(CarStates carStates);

    DataResult<List<ListCarDto>> getAll();

    DataResult<List<ListCarDto>> getByModelYear(short modelYear);

    DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<ListCarDto>> getAllSorted();

    ListCarDto getByCarId(int id);
}
