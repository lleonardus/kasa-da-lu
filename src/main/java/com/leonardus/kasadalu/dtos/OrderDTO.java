package com.leonardus.kasadalu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;

    private FrenchToastDTO frenchToast;

    private Integer quantity;

    private Date orderedAt;
}
