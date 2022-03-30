package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.damageRequests.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageReponses.CarDamageDto;
import com.etiya.rentACar.business.responses.damageReponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface DamageService {

    CarDamageDto getById(int id);

    Result add(CreateDamageRequest createDamageRequest);

    Result update(UpdateDamageRequest updateCarDamageRequest);

    Result delete(DeleteDamageRequest deleteCarDamageRequest);

    DataResult<List<ListDamageDto>> getAll();

    DataResult< List<ListDamageDto> >getByCarId(int id);

    DataResult<  List<ListDamageDto> >getAllPaged(int pageNo, int pageSize);

    DataResult<List<ListDamageDto>> getAllSorted(String option, String field);


}
