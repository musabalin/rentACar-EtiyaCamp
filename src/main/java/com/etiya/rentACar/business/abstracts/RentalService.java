package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeliveryCarRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.stereotype.Service;

@Service
public interface RentalService {

    Result add(CreateRentalRequest rentalRequest);

    Result delete(DeleteRentalRequest deleteRentalRequest);

    Result update(UpdateRentalRequest updateRentalRequest);

    Result deliveryCar(DeliveryCarRequest deliveryCarRequest);


}
