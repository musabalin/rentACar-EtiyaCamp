package com.etiya.rentACar.business.responses.maintenanceResponses;

import com.etiya.rentACar.entities.concretes.Durum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListMaintenanceDto {

    private int maintenanceId;
    private String carDescription;
    private String dateAdded;
    private String dateReturned;
    private String statusName;
    private Durum durum;
}
