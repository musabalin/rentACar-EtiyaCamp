package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;

import java.util.List;

public interface CarService {

    void add(CreateCarRequest createCarRequest);
    List<ListCarDto> getAll();
}
