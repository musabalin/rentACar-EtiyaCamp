package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.InvoiceService;
import com.etiya.rentACar.business.requests.invoicesRequests.CreateInvoicesRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.DeleteInvoicesRequest;
import com.etiya.rentACar.business.requests.invoicesRequests.UpdateInvoicesRequest;
import com.etiya.rentACar.business.responses.invoicesResponses.ListInvoicesDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

    private InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @PostMapping("/add")
    public Result add(@RequestBody CreateInvoicesRequest createInvoicesRequest){
        return this.invoiceService.add(createInvoicesRequest);
    }
    @PutMapping("/update")
    public Result update(@RequestBody UpdateInvoicesRequest updateInvoicesRequest){
        return this.invoiceService.update(updateInvoicesRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteInvoicesRequest deleteInvoicesRequest){
        return this.invoiceService.delete(deleteInvoicesRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<ListInvoicesDto>> getAll(){
        return this.invoiceService.getAll();
    }

    @GetMapping("/findCreateDate")
    public DataResult<List<ListInvoicesDto>> findCreateDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate ){
        return this.invoiceService.findByCreateDateBetween(startDate,endDate);
    }
    @GetMapping("/getAllCustomerId")
    public DataResult<List<ListInvoicesDto>> getByCustomerId(@RequestParam int customerId){
        return this.invoiceService.getByCustomerId(customerId);
    }


}
