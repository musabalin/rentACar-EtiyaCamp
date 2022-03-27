package com.etiya.rentACar.business.requests.maintananceRequests;

import com.etiya.rentACar.entities.concretes.Durum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceRequest {


    @JsonIgnore
    private int maintenanceId;
    private int carId;
    private String dateAdded;
    @JsonIgnore
    private Durum durum = Durum.Maintenance;


}
