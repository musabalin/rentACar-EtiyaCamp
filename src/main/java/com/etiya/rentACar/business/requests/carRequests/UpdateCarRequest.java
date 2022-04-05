package com.etiya.rentACar.business.requests.carRequests;

import com.etiya.rentACar.entities.concretes.CarStates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

    private int id;
    private double dailyPrice;
    private String description;
    private short modelYear;
    private int colorId;
    private int brandId;
    private CarStates statusName;
    private int cityId;
    private int kilometer;

}
