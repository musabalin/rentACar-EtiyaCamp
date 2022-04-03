package com.etiya.rentACar.business.responses.additionalServiceOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdditionalServiceOrderDto {
    private int id;
    private int rental_id;
    private String additionalServiceName;
    private double additionalServicePrice;
}
