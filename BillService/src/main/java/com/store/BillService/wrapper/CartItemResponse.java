package com.store.BillService.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.store.BillService.entity.CartItem;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemResponse {

    private String productName;
    private BigDecimal costPerItem;
    private BigDecimal salesTaxPerItem;
    private BigDecimal quantity;
    private BigDecimal purchasedCost;
    private BigDecimal purchasedSalesTax;

    public static Set<CartItemResponse> map (List<CartItem> cartItem){
        Set<CartItemResponse> cartItemResponseList = new HashSet<>();
        cartItem.forEach(item->{
                        cartItemResponseList.add(CartItemResponse.builder()
                        .productName(item.getProduct().getName())
                                .costPerItem(item.getPurchaseCost().divide(new BigDecimal(item.getQuantity())))
                                .salesTaxPerItem(item.getPurchaseSalesTax().divide(new BigDecimal(item.getQuantity())))
                                .quantity(new BigDecimal(item.getQuantity()))
                                .purchasedCost(item.getPurchaseCost())
                                .purchasedSalesTax(item.getPurchaseSalesTax())
                                .build()
                        );
        });
        return cartItemResponseList;
    }
}
