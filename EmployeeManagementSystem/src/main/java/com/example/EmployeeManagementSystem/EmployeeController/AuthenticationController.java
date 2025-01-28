package com.example.EmployeeManagementSystem.EmployeeController;

import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.Entity.Account;
import com.example.EmployeeManagementSystem.shared.security.JwtService;
import com.example.EmployeeManagementSystem.Service.impl.AuthenticationServiceImpl;
import com.example.EmployeeManagementSystem.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationServiceImpl authenticationServiceImpl;

    public AuthenticationController(JwtService jwtService, AuthenticationServiceImpl authenticationServiceImpl) {
        this.jwtService = jwtService;
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDTO loginUserDto) {
        Account authenticatedUser = authenticationServiceImpl.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}