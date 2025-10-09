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

    public void updateEmployee(Employee employee) {
        employeeRespository.save(employee);
    }

    public Optional<Employee> getEmployeeByUsername(String username) {
        return employeeRespository.findEmployeeByEmployeeName(username);
    }

    public boolean authenticate(String username, String password) {
        Optional<Employee> optionalEmployee = employeeRespository.findEmployeeByEmployeeName(username);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (employee.getEmployeePassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    public void deleteEmployee(Integer id) {
        employeeRespository.deleteById(id);
    }
}