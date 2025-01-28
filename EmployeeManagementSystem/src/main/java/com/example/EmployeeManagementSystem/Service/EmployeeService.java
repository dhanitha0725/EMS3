package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.response.LoginResponse;

import java.util.List;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

    String updateEmployee(EmployeeDTO employeeDTO, int employeeId);

    void deleleEmployee(int employeeId);

    Employee getEmployeeById(int employeeId);

    List<Employee> getEmployeesByFirstName(String firstName);

    List<Employee> getEmployeesByLastName(String lastName);


}
