package com.example.EmployeeManagementSystem.shared.exceptions;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final int code;

    public GeneralException(String message) {
        this(500, message);
    }

    public GeneralException(int code, String message) {
        super(message);
        this.code = code;
    }
}
