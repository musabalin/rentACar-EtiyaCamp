package com.etiya.rentACar.business.requests.additionalServiceOrderRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceOrderRequest {
    @JsonIgnore
    private int id;

    private int additionalServiceId;

    private int rentalId;
}
