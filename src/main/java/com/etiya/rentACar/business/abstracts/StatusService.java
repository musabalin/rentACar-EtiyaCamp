package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.statusRequests.CreateStatusRequest;

public interface StatusService {

    void add(CreateStatusRequest createStatusRequest);

}
