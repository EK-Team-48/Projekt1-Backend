package com.example.projekt1backend.customer;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return new ResponseEntity<>(service.findAllCustomer(),HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        service.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
