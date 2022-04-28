package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.responses.additionalService.AdditionalServiceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface AdditionalServiceService {

    DataResult<AdditionalServiceDto> getById(int id);

    DataResult<List<AdditionalServiceDto>> getAll();


}
