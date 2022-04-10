package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.*;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.CreateInvoicesRequest;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.PaymentDao;
import com.etiya.rentACar.entities.concretes.Invoice;
import com.etiya.rentACar.entities.concretes.Payment;
import com.etiya.rentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;

    private ModelMapperService modelMapperService;

    private RentalService rentalService;

    private AdditionalServiceOrderService additionalServiceOrderService;

    private AdditionalServiceService additionalServiceService;


    public PaymentManager(PaymentDao paymentDao,
                          ModelMapperService modelMapperService,
                          RentalService rentalService,
                          AdditionalServiceOrderService additionalServiceOrderService,
                          AdditionalServiceService additionalServiceService, InvoiceService invoiceService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
        this.additionalServiceOrderService = additionalServiceOrderService;
        this.additionalServiceService = additionalServiceService;
        this.invoiceService = invoiceService;
    }

    private InvoiceService invoiceService;


    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) {


        Payment result = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

        //Araç ekleme
        Rental rental = rentalService.add(createPaymentRequest.getCreateRentalRequest()).getData();
        //Ek hizmetler ekleme
        addAdditionalServiceOrder(createPaymentRequest, rental.getId());
        //Fatura ekleme
        Invoice invoice = addInvoice(createPaymentRequest.getCreateInvoicesRequest(), rental.getId());
        //Toplam ücret
        result.setTotalPrice(calculateTotalPrice(createPaymentRequest, rental.getId()));
        result.setInvoice(invoice);
        result.setRental(rental);

        paymentDao.save(result);
        return new SuccessResult(BusinessMessages.PaymentRequest.PAYMENT_ADD);
    }

    @Override
    public DataResult<List<ListPaymentDto>> getAll() {
        List<Payment> result = paymentDao.findAll();
        List<ListPaymentDto> response = result.stream()
                .map(payment -> modelMapperService.forDto().map(payment, ListPaymentDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListPaymentDto>>(response);
    }

    private double calculateTotalPrice(CreatePaymentRequest createPaymentRequest, int rentalId) {
        RentalDto rentalDto = rentalService.getById(rentalId);
        double totalPrice = 0;
        Period day = Period.between(rentalDto.getDateAdded(), rentalDto.getDateReturned());
        int daysCount = day.getDays();
        if (!Objects.equals(createPaymentRequest.getCreateRentalRequest().getReturnCityId(),
                createPaymentRequest.getCreateRentalRequest().getRentCityId())) {
            totalPrice += 750;
        }

        List<Integer> AdditionalServicesId = createPaymentRequest.getCreateAdditionalServiceOrderRequest()
                .stream().map(CreateAdditionalServiceOrderRequest::getAdditionalServiceId)
                .collect(Collectors.toList());
        for (Integer serviceId : AdditionalServicesId) {
            totalPrice += additionalServiceService.getById(serviceId).getData().getAdditionalServicePrice() * daysCount;
        }


        totalPrice += rentalDto.getDailyPrice() * daysCount;

        return totalPrice;

    }

    private Invoice addInvoice(CreateInvoicesRequest invoiceRequest, int rentalId) {

        var invoice = new CreateInvoicesRequest();
        invoice = modelMapperService.forRequest().map(invoiceRequest, CreateInvoicesRequest.class);
        invoice.setRentalId(rentalId);
        return invoiceService.add(invoice).getData();

    }

    private void addAdditionalServiceOrder(CreatePaymentRequest createPaymentRequest, int rentalId) {

        for (CreateAdditionalServiceOrderRequest additionalServiceOrderRequest :
                createPaymentRequest.getCreateAdditionalServiceOrderRequest()) {
            additionalServiceOrderRequest.setRentalId(rentalId);
            additionalServiceOrderService.add(additionalServiceOrderRequest);
        }

    }

}
