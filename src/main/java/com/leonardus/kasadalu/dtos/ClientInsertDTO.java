package com.leonardus.kasadalu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInsertDTO {
    private String name;
    private String lastname;
    private String phone;
}
