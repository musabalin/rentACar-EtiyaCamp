package com.etiya.rentACar.business.responses.carResponses;

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


}
