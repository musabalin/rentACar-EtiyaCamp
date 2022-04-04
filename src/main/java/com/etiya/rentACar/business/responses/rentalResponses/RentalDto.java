package com.etiya.rentACar.business.responses.rentalResponses;

import com.etiya.rentACar.business.responses.additionalService.AdditionalServiceDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    @JsonIgnore
    private int id;
    private LocalDate dateAdded;
    private LocalDate dateReturned;
    private String carDescription;
    private String customerFirstName;
    private String customerLastName;
    private String rentCityName;
    private String returnCityName;
    private double dailyPrice;
    private List<AdditionalServiceDto> additionalServiceOrder;
}
