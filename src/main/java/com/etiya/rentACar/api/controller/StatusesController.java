package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.StatusService;
import com.etiya.rentACar.business.requests.statusRequests.CreateStatusRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statuses")
public class StatusesController {

    StatusService statusService;

    public StatusesController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateStatusRequest createStatusRequest) {

        statusService.add(createStatusRequest);
    }

}
