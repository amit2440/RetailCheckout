package com.retail.productPrice.dao;

import com.retail.productPrice.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Repository
public interface ProductPriceDao extends JpaRepository<ProductPrice, Long>{

    @Query("SELECT p FROM ProductPrice p WHERE p.productName = :name")
    ProductPrice findPriceByName(@Param("name") String name);
/*
    @Query("SELECT p FROM ProductPrice p ")
    List<ProductPrice> findAllByProductName();*/

    @Modifying
    @Query("update ProductPrice p set p.price = ?1 where p.id = ?2")
    void updatePrice(BigDecimal price, Long id);
}
