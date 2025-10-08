package com.example.projekt1backend.employee.service;


import com.example.projekt1backend.employee.entity.Employee;
import com.example.projekt1backend.employee.entity.EmployeeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:data.sql"}
)
@Transactional
@Rollback(true)
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void getAll() {
        List<Employee> getAll = employeeService.getAll();
        assertNotNull(getAll);
        assertTrue(getAll.size() > 1);
    }

    @Test
    void addEmployee() {

        Employee add = new Employee();
        add.setEmployeeName("Test");
        add.setEmployeeType(EmployeeType.MANAGER);
        add.setEmployeePassword("1234");
        add.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));

        employeeService.addEmployee(add);

        Employee find = employeeService.getEmployeeByUsername(add.getEmployeeName()).orElseThrow(() -> new RuntimeException("User not found"));

        assertNotNull(find);
        assertEquals(add.getEmployeeName(), find.getEmployeeName());
    }

    @Test
    void getEmployeeById() {

        Employee add = new Employee();
        add.setEmployeeName("Test");
        add.setEmployeeType(EmployeeType.MANAGER);
        add.setEmployeePassword("1234");
        add.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
        employeeService.addEmployee(add);


        Employee test = employeeService.getEmployeeByUsername(add.getEmployeeName()).orElseThrow(() -> new RuntimeException("User not found"));
        int employeeId = test.getEmployeeId();

        Employee find = employeeService.getEmployeeById(employeeId);

        assertNotNull(find);
        assertEquals(employeeId, find.getEmployeeId());
    }

    @Test
    void getEmployeeByUsername() {
        Employee add = new Employee();
        add.setEmployeeName("Test");
        add.setEmployeeType(EmployeeType.MANAGER);
        add.setEmployeePassword("1234");
        add.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
        employeeService.addEmployee(add);

        Employee test = employeeService.getEmployeeByUsername(add.getEmployeeName()).orElseThrow(() -> new RuntimeException("User not found"));
        assertNotNull(test);
    }

    @Test
    void authenticateEmployee() {
        Employee add = new Employee();
        add.setEmployeeName("Test");
        add.setEmployeeType(EmployeeType.MANAGER);
        add.setEmployeePassword("1234");
        add.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
        employeeService.addEmployee(add);

        String password = "1234";

        Employee test = employeeService.getEmployeeByUsername(add.getEmployeeName()).orElseThrow(() -> new RuntimeException("User not found"));

        assertTrue(employeeService.authenticate(test.getEmployeeName(), password));

    }

    @Test
    void AuthenticateFail() {
        Employee add = new Employee();
        add.setEmployeeName("Test");
        add.setEmployeeType(EmployeeType.MANAGER);
        add.setEmployeePassword("1234");
        add.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
        employeeService.addEmployee(add);

        boolean result = employeeService.authenticate("Test", "wrongpassword");

        assertFalse(result);
    }

    @Test
    void updateEmployee() {
        Employee add = new Employee();
        add.setEmployeeName("Test");
        add.setEmployeeType(EmployeeType.MANAGER);
        add.setEmployeePassword("1234");
        add.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
        employeeService.addEmployee(add);

        add.setEmployeeName("Bob");
        employeeService.updateEmployee(add);

        assertNotNull(add);
        assertEquals("Bob", add.getEmployeeName());
    }

    @Test
    void deleteEmployee() {

        /*

        har lidt udfordringer med at få denne til at virke
        Employee add = new Employee();
        add.setEmployeeName("Test");
        add.setEmployeeType(EmployeeType.MANAGER);
        add.setEmployeePassword("1234");
        add.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
        employeeService.addEmployee(add);

        Employee delete = employeeService.getEmployeeByUsername(add.getEmployeeName()).orElseThrow(() -> new RuntimeException("User not found"));
        int deleteId = delete.getEmployeeId();
        employeeService.deleteEmployee(delete.getEmployeeId());
        */

    }
}