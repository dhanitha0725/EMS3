package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Dto.Response.EmployeeDetailsDTO;
import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<EmployeeResponseDto> getAllEmployees();


    Optional<EmployeeDetailsDTO> getEmployeeById(int employeeId);
}
