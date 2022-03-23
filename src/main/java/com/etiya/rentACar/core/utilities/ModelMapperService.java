package com.etiya.rentACar.core.utilities;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forRequest();
    ModelMapper forDto();
}
