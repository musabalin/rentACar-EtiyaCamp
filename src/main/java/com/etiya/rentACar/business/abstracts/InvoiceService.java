package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.invoicesRequests.CreateInvoicesRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.DeleteInvoicesRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.UpdateInvoicesRequest;
import com.etiya.rentACar.business.responses.invoicesResponses.ListInvoicesDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.concretes.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    DataResult<Invoice> add(CreateInvoicesRequest createInvoicesRequest);

    Result update(UpdateInvoicesRequest updateInvoicesRequest);

    Result delete(DeleteInvoicesRequest deleteInvoicesRequest);

    DataResult<List<ListInvoicesDto>> getAll();

    DataResult<List<ListInvoicesDto>> getByCustomerId(int customerId);

    DataResult<List<ListInvoicesDto>> findByCreateDateBetween(LocalDate startDate, LocalDate endDate);
}
