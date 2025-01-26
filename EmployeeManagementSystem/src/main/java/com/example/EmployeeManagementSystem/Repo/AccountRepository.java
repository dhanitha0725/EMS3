package com.example.EmployeeManagementSystem.Repo;

import com.example.EmployeeManagementSystem.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Integer> {


}
