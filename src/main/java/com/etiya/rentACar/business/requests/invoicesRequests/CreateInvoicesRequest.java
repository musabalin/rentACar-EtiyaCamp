package com.etiya.rentACar.business.requests.invoicesRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoicesRequest {

    @JsonIgnore
    private int id;

    private String invoiceNumber;

    private LocalDate createDate;

    private double totalPrice;

    private int customerId;

    private int rentalId;
}
