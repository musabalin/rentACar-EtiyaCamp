package com.etiya.rentACar.business.requests.carRequests;

import com.etiya.rentACar.entities.concretes.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusRequest {
    private int id;
    private CarStates statusName;
    private int cityId;

/*
    private int id;
    private double dailyPrice;
    private String description;
    private short modelYear;

    private String colorName;
    private String brandName;
    private String cityName;

    private CarStates statusName;
    private int cityId;*/
}
