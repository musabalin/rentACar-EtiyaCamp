package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.core.utilities.results.Result;

public interface AdditionalServiceOrderService {

    Result add(CreateAdditionalServiceOrderRequest createAdditionalServiceOrderRequest);
}
