package com.etiya.rentACar.business.requests.invoicesRequests;

import java.time.LocalDate;

public class UpdateInvoicesRequest {
    private int id;

    private String invoiceNumber;

    private LocalDate createDate;

    private LocalDate rentDate;

    private LocalDate returnDate;

    private int totalRentDay;

    private double totalPrice;

    private int customerId;

    private int rentalId;
}
