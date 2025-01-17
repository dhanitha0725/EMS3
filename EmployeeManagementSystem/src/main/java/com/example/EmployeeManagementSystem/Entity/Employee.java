package com.example.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @Column(name = "employee_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(name = "employee_name", length = 255)
    private String employeeName;

    @Column(name = "phone", length = 10)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Employee(String employeename, String phone) {
        this.employeeName = employeename;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", employeename='" + employeeName + '\'' +
                ", phone=" + phone +
                '}';
    }
}
