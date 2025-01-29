package com.example.EmployeeManagementSystem.Service.impl;

import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Repo.EmployeeRepo;
import com.example.EmployeeManagementSystem.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final EmployeeRepo employeeRepo;

    public UserServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeRepo.findAllEmployeesAsDto();

        if (employees.isEmpty()) {
            throw new NoSuchElementException("No employee found");
        }else {
            return employees;
        }
    }

}