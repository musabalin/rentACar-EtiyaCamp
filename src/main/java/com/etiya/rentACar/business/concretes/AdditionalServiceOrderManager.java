package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalServiceOrderService;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.responses.additionalService.AdditionalServiceDto;
import com.etiya.rentACar.business.responses.additionalServiceOrder.ListAdditionalServiceOrderDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.AdditionalServiceOrderDao;
import com.etiya.rentACar.entities.concretes.AdditionalService;
import com.etiya.rentACar.entities.concretes.AdditionalServiceOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceOrderManager implements AdditionalServiceOrderService {

    private AdditionalServiceOrderDao additionalServiceOrderDao;
    private ModelMapperService modelMapperService;

    public AdditionalServiceOrderManager(AdditionalServiceOrderDao additionalServiceOrderDao, ModelMapperService modelMapperService) {
        this.additionalServiceOrderDao = additionalServiceOrderDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateAdditionalServiceOrderRequest createAdditionalServiceOrderRequest) {
        AdditionalServiceOrder result = modelMapperService.forRequest().map(createAdditionalServiceOrderRequest, AdditionalServiceOrder.class);
        additionalServiceOrderDao.save(result);
        return new SuccessResult("Ek hizmetler eklendi..");
    }

    @Override
    public DataResult<List<ListAdditionalServiceOrderDto>> getAll() {


        List<AdditionalServiceOrder> result = additionalServiceOrderDao.findAll();
        List<ListAdditionalServiceOrderDto> response = result.stream()
                .map(additionalService -> modelMapperService.forDto().map(additionalService, ListAdditionalServiceOrderDto.class))
                .collect(Collectors.toList());


        return new SuccessDataResult<List<ListAdditionalServiceOrderDto>>(response);

    }
}
