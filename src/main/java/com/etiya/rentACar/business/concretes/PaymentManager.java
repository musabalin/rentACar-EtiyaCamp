package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.*;
import com.etiya.rentACar.business.adapters.PosService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.additionalServiceOrderRequest.CreateAdditionalServiceOrderRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.CreateInvoicesRequest;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.core.crossCuttingConserns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.modelMapperService.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.PaymentDao;
import com.etiya.rentACar.entities.concretes.CreditCard;
import com.etiya.rentACar.entities.concretes.Invoice;
import com.etiya.rentACar.entities.concretes.Payment;
import com.etiya.rentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private InvoiceService invoiceService;

    private PosService posService;


    public PaymentManager(PaymentDao paymentDao,
                          ModelMapperService modelMapperService,
                          RentalService rentalService,
                          AdditionalServiceOrderService additionalServiceOrderService,
                          AdditionalServiceService additionalServiceService, InvoiceService invoiceService, PosService posService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
        this.additionalServiceOrderService = additionalServiceOrderService;
        this.additionalServiceService = additionalServiceService;
        this.invoiceService = invoiceService;
        this.posService = posService;
    }


    @Override
    @Transactional
    public Result add(CreatePaymentRequest createPaymentRequest) {

        checkCreditCard(createPaymentRequest);

        Payment result = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

        //Araç ekleme
        Rental rental = rentalService.add(createPaymentRequest.getCreateRentalRequest()).getData();
        //Ek hizmetler ekleme
        addAdditionalServiceOrder(createPaymentRequest, rental.getId());
        //Fatura ekleme
        Invoice invoice = addInvoice(createPaymentRequest, rental.getId());
        //Toplam ücret
        result.setTotalPrice(calculateTotalPrice(createPaymentRequest));
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

    private double calculateTotalPrice(CreatePaymentRequest createPaymentRequest) {

        double totalPrice = 0;
        Period day = Period.between(createPaymentRequest.getCreateRentalRequest().getDateAdded(),
                createPaymentRequest.getCreateRentalRequest().getDateReturned());
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


        totalPrice += createPaymentRequest.getCreateRentalRequest().getDailyPrice() * daysCount;

        return totalPrice;

    }

    private void checkCreditCard(CreatePaymentRequest createPaymentRequest) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(createPaymentRequest.getCreditCardNo());
        creditCard.setExpirationDate(createPaymentRequest.getExpirationDate());
        creditCard.setCvv(createPaymentRequest.getCvv());
        if (!posService.makePayment(creditCard)) {
            throw new BusinessException("Kart geçersiz");
        }
    }

    private Invoice addInvoice(CreatePaymentRequest invoiceRequest, int rentalId) {


        var invoice = new CreateInvoicesRequest();
        invoice = modelMapperService.forRequest().map(invoiceRequest.getCreateInvoicesRequest(), CreateInvoicesRequest.class);
        invoice.setTotalPrice(calculateTotalPrice(invoiceRequest));
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
