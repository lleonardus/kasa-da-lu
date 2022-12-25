package com.leonardus.kasadalu.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StandardError {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;
}
