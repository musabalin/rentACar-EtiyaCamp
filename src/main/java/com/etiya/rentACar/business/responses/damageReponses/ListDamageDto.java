package com.etiya.rentACar.business.responses.damageReponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDamageDto {

    private int damageId;
    private int carId;
    private String carDescription;
    private LocalDate date;
    private String description;
}
