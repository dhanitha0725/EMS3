package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.Entity.Account;

public interface AuthenticationService {
    Account authenticate(LoginDTO input);
}
