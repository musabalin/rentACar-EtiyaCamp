package com.etiya.rentACar.core.utilities.modelMapperService;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forRequest();
    ModelMapper forDto();
}
