package com.example.EmployeeManagementSystem.Repo;

import com.example.EmployeeManagementSystem.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
}
