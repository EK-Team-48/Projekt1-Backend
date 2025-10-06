package com.example.projekt1backend.employee.service;


import com.example.projekt1backend.employee.entity.Employee;
import com.example.projekt1backend.employee.repository.EmployeeRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {

    private final EmployeeRespository employeeRespository;

    public EmployeeService(EmployeeRespository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    public List<Employee> getAll() {
        return employeeRespository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRespository.save(employee);
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRespository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Optional<Employee> getEmployeeByUsername(String username) {
        return employeeRespository.findEmployeeByEmployeeName(username);
    }

    public Employee AuthenticateEmployee(String username, String password) {
        Employee test = employeeRespository.findEmployeeByEmployeeName(username).orElseThrow(() -> new RuntimeException("Employee not found"));
        if (test.getEmployeePassword().equals(password)) {
            return test;
        } else {
            throw new RuntimeException("User password invalid");
        }
    }

    public void deleteEmployee(Integer id) {
        employeeRespository.deleteById(id);
    }
}