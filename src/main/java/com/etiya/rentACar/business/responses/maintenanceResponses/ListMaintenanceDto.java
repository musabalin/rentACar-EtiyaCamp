package com.etiya.rentACar.business.responses.maintenanceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListMaintenanceDto {

    private int maintenanceId;
    private String description;
    private String carDescription;
    private LocalDate dateAdded;
    private LocalDate dateReturned;
}
