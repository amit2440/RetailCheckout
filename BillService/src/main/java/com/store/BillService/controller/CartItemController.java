package com.store.BillService.controller;

import com.store.BillService.entity.CartItem;
import com.store.BillService.service.CartItemService;
import com.store.BillService.wrapper.CartItemRequest;
import com.store.BillService.wrapper.CartItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = {"/cart"})
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    /**
     * Add prodocts to cart
     * @param cartItemRequest
     * @return
     */
    @PostMapping(value = {"/addToCart"})
    public ResponseEntity addtoCart(
            @RequestBody
            @Valid
                    CartItemRequest cartItemRequest
    ){
        cartItemService.addToCart(CartItemRequest.map(cartItemRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Get list of cart products for given bill id
     * @param id
     * @return
     */
    @GetMapping(value ={"getListOfCartItemsForBillId/{id}"})
    public ResponseEntity<Set<CartItemResponse>> getListOfCartItemsForBillId(@PathVariable Long id){
        List<CartItem> listOfCartItemsForBillId = cartItemService.getListOfCartItemsForBillId(id);
        if(listOfCartItemsForBillId.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(CartItemResponse.map(listOfCartItemsForBillId));
    }
}
