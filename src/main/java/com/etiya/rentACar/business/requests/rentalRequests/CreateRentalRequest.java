package com.etiya.rentACar.business.requests.rentalRequests;

import com.etiya.rentACar.entities.concretes.Car;
import com.etiya.rentACar.entities.concretes.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    @JsonIgnore
    private int id;

    private LocalDate dateAdded;

    private LocalDate dateReturned;

    private int carId;

    private int customerId;

    private int rentCityId;

    private int returnCityId;

    private double dailyPrice;

    private List<Integer> additionalServiceId;

}
