package com.etiya.rentACar.business.responses.rentalResponses;

import com.etiya.rentACar.entities.concretes.Car;
import com.etiya.rentACar.entities.concretes.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {


    @JsonIgnore
    private int id;
    private LocalDate dateAdded;
    private LocalDate dateReturned;
    private String carDescription;
    private String customerFirstName;
    private String customerLastName;
    private String rentCityName;
    private String returnCityName;
    private double totalPrice;
}
