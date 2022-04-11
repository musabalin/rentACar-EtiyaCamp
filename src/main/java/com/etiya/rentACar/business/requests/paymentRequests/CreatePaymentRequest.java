package com.etiya.rentACar.business.requests.paymentRequests;

import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.CreateInvoicesRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.entities.concretes.AdditionalServiceOrder;
import com.etiya.rentACar.entities.concretes.Invoice;
import com.etiya.rentACar.entities.concretes.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    @JsonIgnore
    private int id;

    private String creditCardFirstName;

    private String creditCardLastName;

    private String creditCardNo;

    private String expirationDate;

    private String cvv;

    private CreateRentalRequest createRentalRequest;

    private List<CreateAdditionalServiceOrderRequest> createAdditionalServiceOrderRequest;

    private CreateInvoicesRequest createInvoicesRequest;

}
