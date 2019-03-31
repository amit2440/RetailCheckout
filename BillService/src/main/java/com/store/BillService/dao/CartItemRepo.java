package com.store.BillService.dao;

import com.store.BillService.entity.CartItem;
import com.store.BillService.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends CrudRepository<CartItem,Long> {

    @Query("SELECT c FROM CartItem c WHERE c.bill.id = :billId")
    List<CartItem> getListOfCartItemsForBillId(@Param("billId") Long billId);
}
