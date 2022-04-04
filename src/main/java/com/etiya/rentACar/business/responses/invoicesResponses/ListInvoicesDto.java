package com.etiya.rentACar.business.responses.invoicesResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInvoicesDto {
    private int id;
    private String invoicesNumber;
    private LocalDate rentDate;
    private LocalDate createDate;
    private LocalDate returnDate;
    private double totalPrice;
    private int totalRentDay;
    private int customerId;
    private String customerFirstName;
    private String customerLastName;
}
