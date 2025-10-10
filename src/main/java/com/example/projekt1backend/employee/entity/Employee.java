package com.example.projekt1backend.employee.entity;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false)
    private String employeePassword;

    @Column(nullable = false)
    private Date employeeCreatedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeType employeeType;

    public Employee() {
    }

    public Employee(Integer employeeId, String employeeName, Date employeeCreatedDate, EmployeeType employeeType, String employeePassword) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeCreatedDate = employeeCreatedDate;
        this.employeeType = employeeType;
        this.employeePassword = employeePassword;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getEmployeeCreatedDate() {
        return employeeCreatedDate;
    }

    public void setEmployeeCreatedDate(Date employeeCreatedDate) {
        this.employeeCreatedDate = employeeCreatedDate;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
}
