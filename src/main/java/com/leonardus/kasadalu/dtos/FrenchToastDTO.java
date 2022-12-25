package com.leonardus.kasadalu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrenchToastDTO {
    private Long id;

    private String flavor;

    private Double price;
}
