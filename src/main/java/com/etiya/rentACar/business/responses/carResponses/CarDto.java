package com.etiya.rentACar.business.responses.carResponses;

import com.etiya.rentACar.entities.concretes.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private int id;
    private double dailyPrice;
    private String description;
    private short modelYear;

    private String colorName;
    private String brandName;
    private String cityName;

    private CarStates status;
    private int cityId;
    private int kilometer;


}
