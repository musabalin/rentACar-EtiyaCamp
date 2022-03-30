package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentACar.business.requests.customerRequests.DeleteCustomerRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Result add(CreateCustomerRequest createCustomerRequest);

    Result delete(DeleteCustomerRequest deleteCustomerRequest);

    Result update(UpdateRentalRequest updateRentalRequest);
}
