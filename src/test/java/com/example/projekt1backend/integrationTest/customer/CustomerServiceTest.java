package com.example.projekt1backend.integrationTest.customer;

import com.example.projekt1backend.customer.entity.Customer;
import com.example.projekt1backend.customer.service.CustomerService;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.service.ReservationService;
import com.example.projekt1backend.screening.service.ScreeningService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:data.sql"}
)
@Transactional
@Rollback(true)
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllCustomer() {
        List<Customer> getAll = customerService.findAllCustomer();
        assertFalse(getAll.isEmpty());
        assertNotNull(getAll);
        assertTrue(getAll.size() > 1);
    }

    @Test
    void addCustomer() {
        Customer testCustomer = new Customer();
        testCustomer.setAge(null);
        testCustomer.setNumber("1234");
        testCustomer.setFirstName("test");
        testCustomer.setLastName("yeye");
        customerService.addCustomer(testCustomer);

        Customer found = customerService.findById(testCustomer.getCustomerId());
        assertNotNull(found);
        assertEquals(testCustomer.getFirstName(), found.getFirstName());

    }

    @Test
    void customerFindById() {
        Customer hej = customerService.findById(1);
        assertNotNull(hej);
        assertEquals("Alice", hej.getFirstName());
    }



}