package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.responses.additionalServiceOrder.AdditionalServiceOrderDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface AdditionalServiceOrderService {

    Result add(CreateAdditionalServiceOrderRequest createAdditionalServiceOrderRequest);


    DataResult<List<AdditionalServiceOrderDto>> getAll();
}
