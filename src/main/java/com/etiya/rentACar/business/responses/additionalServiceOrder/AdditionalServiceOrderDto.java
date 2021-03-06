package com.etiya.rentACar.business.responses.additionalServiceOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceOrderDto {
    private int id;
    private int rentalId;
    private String additionalServiceName;
    private double additionalServicePrice;
}
