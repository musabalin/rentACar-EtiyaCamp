package com.etiya.rentACar.business.requests.maintananceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {

    private int maintenanceId;
    private LocalDate dateAdded;
    private LocalDate dateReturned;
    private String description;
    private String carDescription;
    private int carId;


}
