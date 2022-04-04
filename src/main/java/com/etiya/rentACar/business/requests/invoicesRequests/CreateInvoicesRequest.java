package com.etiya.rentACar.business.requests.invoicesRequests;

import com.etiya.rentACar.entities.concretes.Customer;
import com.etiya.rentACar.entities.concretes.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoicesRequest {

    @JsonIgnore
    private int id;

    private String invoiceNumber;

    private LocalDate createDate;

    private int customerId;

    private int rentalId;
}
