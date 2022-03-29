package com.etiya.rentACar.business.requests.damageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarDamageRequest {

    private int damageId;
}
