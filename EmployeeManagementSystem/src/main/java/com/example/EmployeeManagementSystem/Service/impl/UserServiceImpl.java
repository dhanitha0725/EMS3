package com.example.EmployeeManagementSystem.Service.impl;

import com.example.EmployeeManagementSystem.Entity.Account;
import com.example.EmployeeManagementSystem.Repo.AccountRepository;
import com.example.EmployeeManagementSystem.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final AccountRepository accountRepository;

    public UserServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> allUsers() {
        List<Account> users = new ArrayList<>();

        accountRepository.findAll().forEach(users::add);

        return users;
    }
}