package dev.connectme.connectme.shared.infrastructure.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private Integer status;
    private String message;
    private T body;
}