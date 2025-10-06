package com.example.projekt1backend.employee.controller;


import com.example.projekt1backend.employee.EmployeeDTO;
import com.example.projekt1backend.employee.entity.Employee;
import com.example.projekt1backend.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")

public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }


    @PostMapping("/employee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Employee not able to be created: ", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateLogin(@RequestBody EmployeeDTO employee) {
            try {
                employeeService.AuthenticateEmployee(employee.username(), employee.password());
                return new ResponseEntity<>(employee.username(), HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>("User credentials not valid", HttpStatus.UNAUTHORIZED);
            }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("User deleted", HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

}
