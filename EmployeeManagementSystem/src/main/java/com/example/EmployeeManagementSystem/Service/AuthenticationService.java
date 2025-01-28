package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.Entity.Account;

import java.util.Optional;

public interface AuthenticationService {
    Account authenticate(LoginDTO input);

    public Optional<?> getCurrentUser();
}
