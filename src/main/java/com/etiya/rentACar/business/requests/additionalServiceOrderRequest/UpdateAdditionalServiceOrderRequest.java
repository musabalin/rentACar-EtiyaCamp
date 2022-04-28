package com.etiya.rentACar.business.requests.additionalServiceOrderRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceOrderRequest {

    private int additionalServiceId;

    private int rentalId;
}
