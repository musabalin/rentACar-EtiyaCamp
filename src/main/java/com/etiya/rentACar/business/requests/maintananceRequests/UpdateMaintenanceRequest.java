package com.etiya.rentACar.business.requests.maintananceRequests;

import com.etiya.rentACar.entities.concretes.Durum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {


    private int maintenanceId;
    private int carId;
    private String dateReturned;
    private Durum durum;
}
