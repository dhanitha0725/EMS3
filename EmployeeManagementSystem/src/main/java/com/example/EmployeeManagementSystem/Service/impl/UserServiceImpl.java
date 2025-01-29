package com.example.EmployeeManagementSystem.Service.impl;

import com.example.EmployeeManagementSystem.Dto.Response.EmployeeDetailsDTO;
import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Repo.EmployeeRepo;
import com.example.EmployeeManagementSystem.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public Optional<EmployeeDetailsDTO> getEmployeeById(int employeeId) {

        return employeeRepo.findEmployeeById(employeeId);
    }


}