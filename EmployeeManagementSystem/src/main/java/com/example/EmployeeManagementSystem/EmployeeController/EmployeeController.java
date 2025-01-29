package com.example.EmployeeManagementSystem.EmployeeController;
import com.example.EmployeeManagementSystem.Dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;


    @PostMapping(path = "/save")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        try {
            String responseMessage = employeeService.addEmployee(employeeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save employee: " + e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable int employeeId){
        try {
            String responseMessage = employeeService.updateEmployee(employeeDTO,employeeId);
            return ResponseEntity.ok(responseMessage);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId){
        employeeService.deleleEmployee(employeeId);
        return ResponseEntity.ok().body("Employee with ID " + employeeId + " has been deleted successfully.");
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable int employeeId){

        try {
             Employee employeeResponse = employeeService.getEmployeeById(employeeId);
            return ResponseEntity.ok(employeeResponse);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + employeeId + " not found.");
        }
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeesByFirstName/{firstName}")
    public ResponseEntity<Object> getEmployeesByFirstName(@PathVariable String firstName){
        try {
            List<Employee> employees = employeeService.getEmployeesByFirstName(firstName);
            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with first name " + firstName + " not found.");
        }
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeesByLastName/{lastName}")
    public ResponseEntity<Object> getEmployeesByLastName(@PathVariable String lastName){
        try {
            List<Employee> employees = employeeService.getEmployeesByLastName(lastName);
            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with last name " + lastName + " not found.");
        }
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeesByEmail/{email}")
    public ResponseEntity<Object> getEmployeesByEmail(@PathVariable String email){

        try {
            Employee employees = employeeService.getEmployeesByEmail(email);
            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with email " + email + " not found.");
        }

    }


}