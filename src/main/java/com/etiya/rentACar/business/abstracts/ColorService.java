package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.responses.colorResponse.ListColorDto;

import java.util.List;


public interface ColorService {
    void add(CreateColorRequest createColorRequest);
    List<ListColorDto> getAll();
}
