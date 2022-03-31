package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentACar.core.utilities.results.Result;


public interface CityService {

    Result add(CreateCityRequest createCityRequest);

    Result delete(DeleteCityRequest deleteCityRequest);

    Result update(UpdateCityRequest updateCityRequest);
}
