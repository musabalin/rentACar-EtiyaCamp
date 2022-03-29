package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.entities.concretes.Car;
import com.etiya.rentACar.entities.concretes.CarStates;

import java.util.List;

public interface CarService {

    void add(CreateCarRequest createCarRequest);

    void update(UpdateCarRequest carRequest);

    void updateMaintenanceStatus(int id);

    CarDto getById(int id);

    List<ListCarDto> getAllStatus(CarStates carStates);

    List<ListCarDto> getAll();

    List<ListCarDto> getByModelYear(short modelYear);

    List<ListCarDto> getAllPaged(int pageNo, int pageSize);

    List<ListCarDto> getAllSorted();

    ListCarDto getByCarId(int id);
}
