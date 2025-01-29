package com.example.EmployeeManagementSystem.EmployeeController;

import com.example.EmployeeManagementSystem.Dto.Response.EmployeeDetailsDTO;
import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Service.AuthenticationService;
import com.example.EmployeeManagementSystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PreAuthorize( "hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> employees = userService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity<Optional<EmployeeDetailsDTO>> getEmployeeById(@PathVariable int employeeId) {

        try {
            Optional<EmployeeDetailsDTO> employeeDetails = userService.getEmployeeById(employeeId);
            return ResponseEntity.ok(employeeDetails);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}