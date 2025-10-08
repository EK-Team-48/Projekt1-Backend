package com.example.projekt1backend.customer.service;

import com.example.projekt1backend.customer.entity.Customer;
import com.example.projekt1backend.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
