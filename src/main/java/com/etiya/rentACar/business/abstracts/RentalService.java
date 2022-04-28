package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeliveryCarRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalService {
    DataResult<Rental> add(CreateRentalRequest rentalRequest);

    Result delete(DeleteRentalRequest deleteRentalRequest);

    Result update(UpdateRentalRequest updateRentalRequest);

    Result deliveryCar(DeliveryCarRequest deliveryCarRequest);

    RentalDto getById(int rentalId);

    DataResult<List<ListRentalDto>> getAll();
}
