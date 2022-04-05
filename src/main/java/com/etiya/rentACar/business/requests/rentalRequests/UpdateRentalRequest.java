package com.etiya.rentACar.business.requests.rentalRequests;

import com.etiya.rentACar.entities.concretes.Car;
import com.etiya.rentACar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentalRequest {
    private int id;

    private LocalDate dateAdded;

    private LocalDate dateReturned;

    private Car car;

    private Customer customer;

    private int rentCity;

    private int returnCity;

    private int afterRentKilometer;
    private int beforeRentKilometer;

}
