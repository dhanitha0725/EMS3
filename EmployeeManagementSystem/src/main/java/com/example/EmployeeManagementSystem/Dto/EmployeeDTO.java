package com.example.EmployeeManagementSystem.Dto;

public class EmployeeDTO {

    private int employeeid;
    private String employeename;
    private String email;
    private int phone;
    private String password;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int employeeid, String employeename, String email, int phone, String password) {
        this.employeeid = employeeid;
        this.employeename = employeename;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeid=" + employeeid +
                ", employeename='" + employeename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
