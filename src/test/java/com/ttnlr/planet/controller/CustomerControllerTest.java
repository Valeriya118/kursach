package com.ttnlr.planet.controller;

import com.ttnlr.planet.model.Customer;
import com.ttnlr.planet.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCustomers_ReturnsOk() {
        int page = 1;
        Page<Customer> buyersPage = mock(Page.class);
        when(customerService.getAllCustomers(page)).thenReturn(buyersPage);

        ResponseEntity<Page<Customer>> response = customerController.getAllCustomers(page);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(buyersPage, response.getBody());
    }

    @Test
    void getCustomerById_ReturnsOk() {
        Long id = 1L;
        Customer customer = new Customer();
        when(customerService.getCustomerById(id)).thenReturn(Optional.of(customer));

        ResponseEntity<Customer> response = customerController.getCustomerById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void getCustomerById_ReturnsNotFound() {
        Long id = 1L;
        when(customerService.getCustomerById(id)).thenReturn(Optional.empty());

        ResponseEntity<Customer> response = customerController.getCustomerById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateCustomerById_ReturnsOk() {
        Long id = 1L;
        Customer updatedCustomer = new Customer();
        when(customerService.putCustomerById(id, updatedCustomer)).thenReturn(Optional.of(updatedCustomer));

        ResponseEntity<Customer> response = customerController.updateCustomerById(id, updatedCustomer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCustomer, response.getBody());
    }

    @Test
    void updateCustomerById_ReturnsNotFound() {
        Long id = 1L;
        Customer updatedCustomer = new Customer();
        when(customerService.putCustomerById(id, updatedCustomer)).thenReturn(Optional.empty());

        ResponseEntity<Customer> response = customerController.updateCustomerById(id, updatedCustomer);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteCustomerById_ReturnsOk() {
        Long id = 1L;
        ResponseEntity<Void> response = customerController.deleteCustomerById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomerById(id);
    }
}
