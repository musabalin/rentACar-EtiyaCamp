package com.etiya.rentACar.business.requests.carRequests;

import com.etiya.rentACar.entities.concretes.CarStates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    @JsonIgnore
    private int id;

    @NotNull
    @Min(1)
    private double dailyPrice;

    private int kilometer;

    @NotNull
    @Length(min = 2, max = 50)
    private String description;
    @NotNull
    private short modelYear;
    @NotNull
    private int colorId;
    @NotNull
    private int brandId;

    private int cityId;
    @NotNull
    private CarStates statusName;
}
