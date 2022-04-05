package com.etiya.rentACar.business.responses.carResponses;

import com.etiya.rentACar.entities.concretes.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {

    private int id;
    private double dailyPrice;
    private String description;
    private short modelYear;

    private int kilometer;

    private String colorName;
    private String brandName;

    private CarStates statusName;
    private int cityId;


}
