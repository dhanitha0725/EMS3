package com.example.EmployeeManagementSystem.Repo;

import com.example.EmployeeManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeeByLastName(String lastName);

    Optional<Employee> findByAccountId(int id);
}