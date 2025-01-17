package com.example.EmployeeManagementSystem.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmployeeDTO {

    private int employeeId;
    private String employeeName;
    private String email;
    private String phone;
    private String password;

//    public EmployeeDTO(int employeeId, String employeeName, String email, int phone, String password) {
//        this.employeeId = employeeId;
//        this.employeeName = employeeName;
//        this.email = email;
//        this.phone = phone;
//        this.password = password;
//    }

//    @Override
//    public String toString() {
//        return "EmployeeDTO{" +
//                "employeeid=" + employeeId +
//                ", employeename='" + employeeName + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
