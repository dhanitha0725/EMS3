package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Entity.Account;

import java.util.List;

public interface UserService {
    List<EmployeeResponseDto> getAllEmployees();
}
