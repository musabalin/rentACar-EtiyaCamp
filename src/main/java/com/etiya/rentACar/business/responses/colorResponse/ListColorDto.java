package com.etiya.rentACar.business.responses.colorResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListColorDto {
    private int colorId;
    private String name;
}
