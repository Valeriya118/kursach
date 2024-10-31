package com.ttnlr.planet.service;

import com.ttnlr.planet.exception.ExceptionHandler;
import com.ttnlr.planet.model.Customer;
import com.ttnlr.planet.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ExceptionHandler exceptionHandler;
    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Page<Customer> getAllCustomers(int page) {
        int size = 4;
        return customerRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> putCustomerById(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();
            if (updatedCustomer.getName() != null) {
                customerToUpdate.setName(updatedCustomer.getName());
            }
            if (updatedCustomer.getSurname() != null) {
                customerToUpdate.setSurname(updatedCustomer.getSurname());
            }
            if (updatedCustomer.getTelNumber() != null) {
                customerToUpdate.setTelNumber(updatedCustomer.getTelNumber());
            }
            customerRepository.save(customerToUpdate);
        }
        return existingCustomer;
    }


    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
