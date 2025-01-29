package com.example.EmployeeManagementSystem.Repo;

import com.example.EmployeeManagementSystem.Entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);


    boolean existsByEmail( String email);
}
