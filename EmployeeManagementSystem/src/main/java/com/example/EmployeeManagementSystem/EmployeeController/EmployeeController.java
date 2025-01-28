package com.example.EmployeeManagementSystem.EmployeeController;
import com.example.EmployeeManagementSystem.Dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Service.EmployeeService;
import com.example.EmployeeManagementSystem.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;


    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        String id = employeeService.addEmployee(employeeDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable int employeeId){
        try {
            String responseMessage = employeeService.updateEmployee(employeeDTO,employeeId);
            return ResponseEntity.ok(responseMessage);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId){
        employeeService.deleleEmployee(employeeId);
        return ResponseEntity.ok().body("Employee with ID " + employeeId + " has been deleted successfully.");
    }

    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int employeeId){

        try {
             Employee employeeResponse = employeeService.getEmployeeById(employeeId);
            return ResponseEntity.ok(employeeResponse);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + employeeId + " not found.");
        }
    }

    @GetMapping("/getEmployeesByFirstName/{firstName}")
    public ResponseEntity<?> getEmployeesByFirstName(@PathVariable String firstName){
        try {
            List<Employee> employees = employeeService.getEmployeesByFirstName(firstName);
            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with first name " + firstName + " not found.");
        }
    }

    @GetMapping("/getEmployeesByLastName/{lastName}")
    public ResponseEntity<?> getEmployeesByLastName(@PathVariable String lastName){
        try {
            List<Employee> employees = employeeService.getEmployeesByLastName(lastName);
            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with last name " + lastName + " not found.");
        }
    }

//    @GetMapping("/getEmployeesByEmail/{email}")
//    public ResponseEntity<?> getEmployeesByEmail(@PathVariable String email){
//
//        try {
//            Employee employees = employeeService.getEmployeesByEmail(email);
//            return ResponseEntity.ok(employees);
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with email " + email + " not found.");
//        }
//
//    }


}