package com.store.BillService.service;


import com.store.BillService.dao.CartItemRepo;
import com.store.BillService.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CartItemService {
    void addToCart(CartItem cartItem);

    List<CartItem> getListOfCartItemsForBillId(Long id);
}
