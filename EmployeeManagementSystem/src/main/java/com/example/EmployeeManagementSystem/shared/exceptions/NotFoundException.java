package com.example.EmployeeManagementSystem.shared.exceptions;

public class NotFoundException extends GeneralException {
    public NotFoundException(String message) {
        super(404, message);
    }
}