package com.etiya.rentACar.business.requests.carRequests;

import com.etiya.rentACar.entities.concretes.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusRequest {
    private int carId;
    private CarState state;
}
