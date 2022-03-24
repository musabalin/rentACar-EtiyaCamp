package com.etiya.rentACar.api.controller;

import com.etiya.rentACar.business.abstracts.ColorService;
import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.responses.colorResponse.ListColorDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

    ColorService colorService;

    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping(name = "/add")
    void add(@RequestBody CreateColorRequest colorRequests) {
        colorService.add(colorRequests);
    }

    @GetMapping("getall")
    public List<ListColorDto> getall(){
        return colorService.getAll();
    }



}
