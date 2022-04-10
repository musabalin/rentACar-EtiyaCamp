package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.InvoiceService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.invoicesRequests.CreateInvoicesRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.DeleteInvoicesRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.UpdateInvoicesRequest;
import com.etiya.rentACar.business.responses.additionalService.AdditionalServiceDto;
import com.etiya.rentACar.business.responses.invoicesResponses.ListInvoicesDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.InvoiceDao;
import com.etiya.rentACar.entities.concretes.Invoice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceManager implements InvoiceService {

    private InvoiceDao invoiceDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;

    public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService, RentalService rentalService) {
        this.invoiceDao = invoiceDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
    }

    @Override
    public DataResult<Invoice> add(CreateInvoicesRequest createInvoicesRequest) {

        int rentalId = createInvoicesRequest.getRentalId();
        RentalDto rentalDto = this.rentalService.getById(rentalId);

        Period day = Period.between(rentalDto.getDateAdded(), rentalDto.getDateReturned());
        int daysCount = day.getDays();


        Invoice invoice = this.modelMapperService.forRequest().map(createInvoicesRequest, Invoice.class);
        invoice.setRentDate(rentalDto.getDateAdded());
        invoice.setReturnDate(rentalDto.getDateReturned());
        invoice.setTotalPrice(calculateTotalPrice(rentalDto));
        invoice.setTotalRentDay(daysCount);
        this.invoiceDao.save(invoice);


        return new SuccessDataResult<Invoice>(invoice,BusinessMessages.InvoiceMessages.INVOICE_ADD);

    }

    @Override
    public Result update(UpdateInvoicesRequest updateInvoicesRequest) {
        return null;
    }

    @Override
    public Result delete(DeleteInvoicesRequest deleteInvoicesRequest) {
        return null;
    }

    @Override
    public DataResult<List<ListInvoicesDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<List<ListInvoicesDto>> getByCustomerId(int customerId) {
        return null;
    }

    @Override
    public DataResult<List<ListInvoicesDto>> findByCreateDateBetween(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    private double calculateTotalPrice(RentalDto rentalDto) {
        double totalPrice = 0;
        Period day = Period.between(rentalDto.getDateAdded(), rentalDto.getDateReturned());
        int daysCount = day.getDays();
        if (!Objects.equals(rentalDto.getRentCityName(), rentalDto.getReturnCityName())) {
            totalPrice += 750;
        }

        totalPrice += rentalDto.getDailyPrice() * daysCount;
/*
        for (AdditionalServiceDto additionalService : rentalDto.getAdditionalServices()) {
            totalPrice += additionalService.getAdditionalServicePrice() * daysCount;
        }*/

        return totalPrice;

    }
}
