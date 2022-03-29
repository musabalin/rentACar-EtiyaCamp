package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.damageRequests.CreateCarDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteCarDamageRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.damageReponses.CarDamageDto;
import com.etiya.rentACar.business.responses.damageReponses.ListDamageDto;

import java.util.List;

public interface DamageService {

    CarDamageDto getById(int id);

    void add(CreateCarDamageRequest createDamageRequest);

    void update(UpdateCarRequest updateCarRequest);

    void delete(DeleteCarDamageRequest deleteCarDamageRequest);

    List<ListDamageDto> getAll();

    List<ListDamageDto> getByCarId(int id);

    List<ListDamageDto> getAllPaged(int pageNo, int pageSize);

    List<ListDamageDto> getAllSorted(String option, String field);


}
