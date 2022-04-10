package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.PaymentService;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/add")
    public void add(@RequestBody CreatePaymentRequest createPaymentRequest){
        paymentService.add(createPaymentRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListPaymentDto>> getAll(){
        return paymentService.getAll();
    }

}
