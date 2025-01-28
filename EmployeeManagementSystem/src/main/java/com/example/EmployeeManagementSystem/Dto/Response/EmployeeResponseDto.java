package com.example.EmployeeManagementSystem.Dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private int id;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String address;

    private String phone;

    public EmployeeResponseDto(int id, String role, String firstName, String lastName, String address, String email, String phone){
        this.id = id;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }
}
