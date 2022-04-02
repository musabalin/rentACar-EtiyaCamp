package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalServiceOrderService;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.AdditionalServiceOrderDao;
import com.etiya.rentACar.entities.concretes.AdditionalServiceOrder;
import org.springframework.stereotype.Service;

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
        AdditionalServiceOrder result=modelMapperService.forRequest().map(createAdditionalServiceOrderRequest,AdditionalServiceOrder.class);
        additionalServiceOrderDao.save(result);
        return new SuccessResult("Ek hizmetler eklendi..");
    }
}
