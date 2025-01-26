package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.Dto.GetEmployeeDTO;
import com.example.EmployeeManagementSystem.Dto.LoginDTO;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.response.LoginResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeService  {

    String addEmployee(EmployeeDTO employeeDTO);

    LoginResponse loginEmployee(LoginDTO loginDTO);

    String updateEmployee(EmployeeDTO employeeDTO, int employeeId);

    void deleleEmployee(int employeeId);

    List<GetEmployeeDTO> getAllEmployees();
}
