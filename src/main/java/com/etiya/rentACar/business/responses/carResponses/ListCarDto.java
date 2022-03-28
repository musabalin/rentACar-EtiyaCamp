package com.etiya.rentACar.business.responses.carResponses;

import com.etiya.rentACar.entities.concretes.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {

    private int carId;
    private double dailyPrice;
    private String description;
    private short modelYear;

    private String colorName;
    private String brandName;
    private CarState carStatus;


}
