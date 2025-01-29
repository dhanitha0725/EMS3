package com.example.EmployeeManagementSystem.Dto.Response;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeDetailsDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    public String password;


    public EmployeeDetailsDTO(String firstName, String lastName, String address, String email, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
