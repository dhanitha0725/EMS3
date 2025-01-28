package com.example.EmployeeManagementSystem.Service.impl;

import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Entity.Account;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Repo.AccountRepository;
import com.example.EmployeeManagementSystem.Repo.EmployeeRepo;
import com.example.EmployeeManagementSystem.Service.AuthenticationService;
import com.example.EmployeeManagementSystem.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Account authenticate(LoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return accountRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    @Override
    public Optional<?> getCurrentUser() {
        // Get the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new NotFoundException("No authenticated user found."); // No authenticated user
        }

        // Get the username of the current user
        Account currentUser = (Account) authentication.getPrincipal();
        System.out.println("Current user: " + currentUser);
        Account account = accountRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Check if the user is an Employee
        Optional<Employee> employee = employeeRepo.findByAccountId(account.getId());
        if (employee.isPresent()) {
            Employee emp = employee.get();
            // Return the EmployeeResponseDto
            return Optional.of(new EmployeeResponseDto(
                    account.getId(),
                    account.getEmail(),
                    account.getRole(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getAddress(),
                    emp.getPhone()
            ));
        }
        // If no match is found, return an empty Optional
        throw new NotFoundException("No matching user found for the current authentication. Please ensure your credentials are correct and try again.");
    }
}