package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.responses.additionalService.AdditionalServiceDto;
import com.etiya.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
    public  AdditionalServiceDto getById(int id);
}
