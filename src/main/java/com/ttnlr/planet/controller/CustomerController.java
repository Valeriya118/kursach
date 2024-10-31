package com.ttnlr.planet.controller;

import com.ttnlr.planet.model.Customer;
import com.ttnlr.planet.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> addCustomer(@RequestBody @Valid Customer customer,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Обработка ошибок валидации
            return ResponseEntity.badRequest().build();
        }
        customerService.addCustomer(customer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(@RequestParam int page) {
        return ResponseEntity.ok(customerService.getAllCustomers(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.getCustomerById(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> updatedCustomerOptional = customerService.putCustomerById(id, updatedCustomer);
        if (updatedCustomerOptional.isPresent()) {
            return ResponseEntity.ok(updatedCustomerOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }
}
