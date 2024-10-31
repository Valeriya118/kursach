package com.ttnlr.planet.service;

import com.ttnlr.planet.exception.ExceptionHandler;
import com.ttnlr.planet.model.Customer;
import com.ttnlr.planet.model.Employee;
import com.ttnlr.planet.model.Planet;
import com.ttnlr.planet.model.Purchase;
import com.ttnlr.planet.repository.CustomerRepository;
import com.ttnlr.planet.repository.EmployeeRepository;
import com.ttnlr.planet.repository.PlanetRepository;
import com.ttnlr.planet.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PurchaseServiceImpl implements PurchaseService {

    private final ExceptionHandler exceptionHandler;
    private final PurchaseRepository purchaseRepository;
    private final PlanetRepository planetRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void addPurchase(Purchase purchase) {
        Planet planet = purchase.getPlanet();
        Customer customer = purchase.getCustomer();
        Employee employee = purchase.getEmployee();

        if (planet != null && planet.getIdPlanet() != null) {
            planet = planetRepository.findById(planet.getIdPlanet()).orElse(null);
        }
        if (customer != null && customer.getIdCustomer() != null) {
            customer = customerRepository.findById(customer.getIdCustomer()).orElse(null);
        }
        if (employee != null && employee.getIdEmployee() != null) {
            employee = employeeRepository.findById(employee.getIdEmployee()).orElse(null);
        }

        purchase.setPlanet(planet);
        purchase.setCustomer(customer);
        purchase.setEmployee(employee);

        purchaseRepository.save(purchase);

    }


    @Override
    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    @Override
    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public Optional<Purchase> putPurchaseById(Long id, Purchase updatedPurchase) {
        Optional<Purchase> existingPurchase = purchaseRepository.findById(id);
        if(existingPurchase.isPresent()){

            Purchase purchaseToUpdate = existingPurchase.get();
            if(updatedPurchase.getPlanet() != null && updatedPurchase.getPlanet().getIdPlanet() != null) {
                Planet planet = planetRepository.findById(updatedPurchase.getPlanet().getIdPlanet()).orElse(null);
                purchaseToUpdate.setPlanet(planet);
            }

            if(updatedPurchase.getCustomer() != null && updatedPurchase.getCustomer().getIdCustomer() != null) {
                Customer customer = customerRepository.findById(updatedPurchase.getCustomer().getIdCustomer()).orElse(null);
                purchaseToUpdate.setCustomer(customer);
            }

            if(updatedPurchase.getEmployee() != null && updatedPurchase.getEmployee().getIdEmployee() != null) {
                Employee employee = employeeRepository.findById(updatedPurchase.getEmployee().getIdEmployee()).orElse(null);
                purchaseToUpdate.setEmployee(employee);
            }
            purchaseRepository.save(purchaseToUpdate);
        }
        return existingPurchase;
    }



    @Override
    public void deletePurchaseById(Long id) {
        purchaseRepository.deleteById(id);

    }
}