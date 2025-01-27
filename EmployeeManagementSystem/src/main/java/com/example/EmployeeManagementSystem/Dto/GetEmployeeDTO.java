package com.example.EmployeeManagementSystem.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Data

public class GetEmployeeDTO {

    @JsonProperty("employee_id")
    private int employeeId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    private String phone;

    public GetEmployeeDTO(int employeeId, String firstName, String lastName, String address , String phone ) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }
}
