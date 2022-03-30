package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.responses.colorResponse.ListColorDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;


public interface ColorService {
    Result add(CreateColorRequest createColorRequest);

    DataResult<List<ListColorDto>> getAll();
}
