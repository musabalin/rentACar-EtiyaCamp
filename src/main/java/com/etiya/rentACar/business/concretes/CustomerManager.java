package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CustomerService;
import com.etiya.rentACar.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentACar.business.requests.customerRequests.DeleteCustomerRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.core.utilities.modelMapperService.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CustomerDao;
import com.etiya.rentACar.entities.concretes.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;
    private ModelMapperService modelMapperService;

    public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
        this.customerDao = customerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCustomerRequest createCustomerRequest) {
        Customer customer = modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        customerDao.save(customer);

        return new SuccessResult();
    }

    @Override
    public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
        return null;
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        return null;
    }
}
