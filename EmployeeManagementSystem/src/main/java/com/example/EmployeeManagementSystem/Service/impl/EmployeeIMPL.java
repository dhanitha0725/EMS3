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

    @Override
    public LoginResponse  loginEmployee(LoginDTO loginDTO) {
//        String msg = "";
//        Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
//        if (employee1 != null) {
//            String password = loginDTO.getPassword();
//            String encodedPassword = employee1.getPassword();
//            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
//            if (isPwdRight) {
//                Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
//                if (employee.isPresent()) {
//                    return new LoginResponse("Login Success", true);
//                } else {
//                    return new LoginResponse("Login Failed", false);
//                }
//            } else {
//
//                return new LoginResponse("password Not Match", false);
//            }
//        }else {
//            return new LoginResponse("Email not exits", false);
//        }
        return null;

    }

    @Override
    public String updateEmployee(EmployeeDTO employeeDTO, int employeeId) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Employee not found"));

        if (employeeDTO.getFirstName() != null && !employeeDTO.getFirstName().isEmpty()) {
            employee.setFirstName(employeeDTO.getFirstName());
        }

        if (employeeDTO.getLastName() != null && !employeeDTO.getLastName().isEmpty()) {
            employee.setLastName(employeeDTO.getLastName());
        }

        if (employeeDTO.getAddress() != null && !employeeDTO.getAddress().isEmpty()) {
            employee.setAddress(employeeDTO.getAddress());
        }

        if (employeeDTO.getPhone() != null && !employeeDTO.getPhone().isEmpty()) {
            employee.setPhone(employeeDTO.getPhone());
        }

        Account account = employee.getAccount();
        if (account != null) {
            if (employeeDTO.getEmail() != null && !employeeDTO.getEmail().isEmpty()) {
                account.setEmail(employeeDTO.getEmail());
            }
            if (employeeDTO.getPassword() != null && !employeeDTO.getPassword().isEmpty()) {
                account.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
            }
            accountRepository.save(account);
        }else {
            throw new RuntimeException("Account not found for Employee ID: "+ employeeId);
        }

        employeeRepo.save(employee);

        return "Employee Updated Successfully";
    }

}