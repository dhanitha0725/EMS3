package com.example.EmployeeManagementSystem.EmployeeController;

import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Entity.Account;
import com.example.EmployeeManagementSystem.Service.AuthenticationService;
import com.example.EmployeeManagementSystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/currentuser")
    public ResponseEntity<?> authenticatedUser() {
        Optional<?> currentUser = authenticationService.getCurrentUser();

        if (currentUser.isPresent()) {
            return ResponseEntity.ok(currentUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> employees = userService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}