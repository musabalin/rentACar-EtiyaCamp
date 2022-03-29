package com.etiya.rentACar.business.requests.maintananceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMaintenanceRequest {
    private int maintenanceId;
}
