package com.etiya.rentACar.business.responses.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceDto {


    private int id;
    private String name;
    private double price;
}
