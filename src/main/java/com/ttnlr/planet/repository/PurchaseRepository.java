package com.ttnlr.planet.repository;


import com.ttnlr.planet.model.Purchase;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    //общее количество заказов за период
    @Query("SELECT COUNT(p) FROM purchase p WHERE p.dateAddedPurchase BETWEEN :startDate AND :endDate")
    Long countPurchasesBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}