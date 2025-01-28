package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.response.LoginResponse;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

}
