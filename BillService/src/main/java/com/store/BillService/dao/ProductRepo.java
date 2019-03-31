package com.store.BillService.dao;

import com.store.BillService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.barCodeId = :barCodeId")
    Optional<Product> findProductByBarCodeId(@Param("barCodeId") String barCodeId);
}
