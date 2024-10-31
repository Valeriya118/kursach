package com.ttnlr.planet.service;

import com.ttnlr.planet.model.Customer;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void addCustomer(Customer customer);

    Page<Customer> getAllCustomers(int page);

    Optional<Customer> getCustomerById(Long id);

    Optional<Customer> putCustomerById(Long id, Customer updatedCustomer);

    void deleteCustomerById(Long id);

}