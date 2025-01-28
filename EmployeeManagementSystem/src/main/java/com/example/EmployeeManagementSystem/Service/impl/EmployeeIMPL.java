package com.example.EmployeeManagementSystem.Service.impl;

import com.example.EmployeeManagementSystem.Dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.Entity.Account;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Repo.AccountRepository;
import com.example.EmployeeManagementSystem.Repo.EmployeeRepo;
import com.example.EmployeeManagementSystem.Service.EmployeeService;

import com.example.EmployeeManagementSystem.response.LoginResponse;
import com.example.EmployeeManagementSystem.shared.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Account account = new Account(
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword()),
                ApplicationConstants.ROLES.ROLE_EMPLOYEE.name()
        );

        Account savedAccount = accountRepository.save(account);

        Employee employee = new Employee(
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getAddress(),
                employeeDTO.getPhone()
        );

        System.out.println(employeeDTO.getFirstName() + " " + employeeDTO.getLastName());

        employee.setAccount(savedAccount);
        employeeRepo.save(employee);

        return "Registered an Employee Successfully";
    }

}