package com.ttnlr.planet.controller;

import com.ttnlr.planet.model.Purchase;
import com.ttnlr.planet.repository.PurchaseRepository;
import com.ttnlr.planet.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")

public class PurchaseController {
    private final PurchaseService purchaseService;
    private final PurchaseRepository purchaseRepository;

    @PostMapping
    public ResponseEntity<Void> addPurchase(@RequestBody @Valid Purchase purchase,
                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Обработка ошибок валидации
            return ResponseEntity.badRequest().build();
        }
        purchaseService.addPurchase(purchase);
        return ResponseEntity.ok().build();

    }


    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchase(){
        return ResponseEntity.ok(purchaseService.getAllPurchase());

    }


    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id){
        Optional<Purchase> purchaseOptional = purchaseService.getPurchaseById(id);
        if (purchaseOptional.isPresent()) {
            return ResponseEntity.ok(purchaseOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchaseById(@PathVariable Long id, @RequestBody @Validated Purchase updatedPurchase) {
        Optional<Purchase> updatedPurchaseOptional = purchaseService.putPurchaseById(id, updatedPurchase);
        if (updatedPurchaseOptional.isPresent()) {
            return ResponseEntity.ok(updatedPurchaseOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseById(@PathVariable Long id){
        purchaseService.deletePurchaseById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPurchasesBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        Long count = purchaseRepository.countPurchasesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(count);
    }


}