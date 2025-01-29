package com.example.EmployeeManagementSystem.Repo;

import com.example.EmployeeManagementSystem.Dto.Response.EmployeeDetailsDTO;
import com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {


    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeeByLastName(String lastName);

    Optional<Employee> findByAccountId(int id);

    @Query("SELECT new com.example.EmployeeManagementSystem.Dto.Response.EmployeeResponseDto(" +
            "e.employeeId, a.role, e.firstName, e.lastName, e.address, a.email, e.phone) " +
            "FROM Account a JOIN Employee e ON a.id = e.account.id")
    List<EmployeeResponseDto> findAllEmployeesAsDto();


    @Query("SELECT e FROM Employee e WHERE e.account.email = :email")
    Employee findEmployeeByEmail( @Param("email") String email);

    @Query("SELECT new com.example.EmployeeManagementSystem.Dto.Response.EmployeeDetailsDTO(" +
            "e.firstName, e.lastName, e.address, a.email, e.phone, a.password) " +
            "FROM Employee e JOIN e.account a WHERE e.employeeId = :employeeId")
    Optional<EmployeeDetailsDTO> findEmployeeById(@Param("employeeId") int employeeId);
}