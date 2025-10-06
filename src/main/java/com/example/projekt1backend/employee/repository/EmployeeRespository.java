package com.example.projekt1backend.employee.repository;


import com.example.projekt1backend.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EmployeeRespository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findEmployeeByEmployeeName(String employeeName);
}
