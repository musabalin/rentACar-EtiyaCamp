package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.responses.additionalService.AdditionalServiceDto;
import com.etiya.rentACar.core.utilities.modelMapperService.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.rentACar.entities.concretes.AdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

    private final AdditionalServiceDao additionalServiceDao;
    private final ModelMapperService modelMapperService;

    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao,
                                    ModelMapperService modelMapperService) {
        this.additionalServiceDao = additionalServiceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<AdditionalServiceDto> getById(int id) {
        AdditionalService result = additionalServiceDao.getById(id);
        AdditionalServiceDto additionalServiceDto = modelMapperService.forRequest()
                .map(result, AdditionalServiceDto.class);
        return new SuccessDataResult<>(additionalServiceDto);
    }

    @Override
    public DataResult<List<AdditionalServiceDto>> getAll() {
        List<AdditionalService> result = additionalServiceDao.findAll();
        List<AdditionalServiceDto> response = result.stream()
                .map(additionalService -> modelMapperService.forDto().map(additionalService, AdditionalServiceDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

}
