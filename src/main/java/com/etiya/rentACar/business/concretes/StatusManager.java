package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.StatusService;
import com.etiya.rentACar.business.requests.statusRequests.CreateStatusRequest;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.StatusDao;
import com.etiya.rentACar.entities.concretes.Status;
import org.springframework.stereotype.Service;

@Service
public class StatusManager implements StatusService {

    private StatusDao statusDao;
    private ModelMapperService modelMapperService;

    public StatusManager(StatusDao statusDao, ModelMapperService modelMapperService) {
        this.statusDao = statusDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(CreateStatusRequest createStatusRequest) {
        Status status=modelMapperService.forRequest().map(createStatusRequest,Status.class);
        statusDao.save(status);
    }
}
