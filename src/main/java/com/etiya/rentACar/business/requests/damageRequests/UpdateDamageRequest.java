package com.etiya.rentACar.business.requests.damageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDamageRequest {

    private int damageId;
    private int carId;
    private LocalDate date;
    private String description;
}
