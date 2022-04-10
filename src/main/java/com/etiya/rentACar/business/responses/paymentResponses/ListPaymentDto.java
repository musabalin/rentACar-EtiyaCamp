package com.etiya.rentACar.business.responses.paymentResponses;

import com.etiya.rentACar.business.responses.invoicesResponses.InvoicesDto;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentDto {

    private String rentalCustomerFirstName;
    private String rentalCustomerLastName;
    private List<ListRentalDto> rentalDtos;
    private List<InvoicesDto> invoicesDtos;
    private double totalPrice;
}
