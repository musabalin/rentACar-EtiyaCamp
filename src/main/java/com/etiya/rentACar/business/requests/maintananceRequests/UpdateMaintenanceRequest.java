package com.etiya.rentACar.business.requests.maintananceRequests;

import com.etiya.rentACar.entities.concretes.CarState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {

    @JsonIgnore
    private int maintenanceId;
    private int carId;
    private LocalDate dateReturned;
    private CarState stateName;


}
