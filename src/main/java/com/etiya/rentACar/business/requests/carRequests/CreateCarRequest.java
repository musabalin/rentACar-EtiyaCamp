package com.etiya.rentACar.business.requests.carRequests;

import com.etiya.rentACar.entities.concretes.CarState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    @JsonIgnore
    private int carId;
    private double dailyPrice;
    private String description;
    private short modelYear;
    private int colorId;
    private int brandId;
    private CarState statusName;
}
