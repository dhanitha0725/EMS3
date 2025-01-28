package com.example.EmployeeManagementSystem.Service.impl;

import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Repo.EmployeeRepo;
import com.example.EmployeeManagementSystem.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final EmployeeRepo employeeRepo;

    public UserServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepo.findAllEmployeesAsDto();
    }

}