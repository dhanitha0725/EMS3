package com.example.EmployeeManagementSystem.Dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private int employeeId;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public EmployeeResponseDto(int employeeId, String role, String firstName, String lastName, String address, String email, String phone){
        this.employeeId = employeeId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
