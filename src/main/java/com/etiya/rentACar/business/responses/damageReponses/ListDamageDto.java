package com.etiya.rentACar.business.responses.damageReponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDamageDto {

    private int damageId;
    private int carId;
    private String carDescription;
    private String date;
    private String description;

    public int getCarId() {
        return carId;
    }
}
