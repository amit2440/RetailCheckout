package com.store.BillService.wrapper;

import com.store.BillService.entity.CartItem;
import com.store.BillService.entity.Category;
import com.store.BillService.entity.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CartItemRequest  {

    @NotNull
    private String barCodeId;

    @NotNull
    private Float quantity ;

    public static CartItem map(CartItemRequest cartItemRequest){
        return CartItem.builder()
                .quantity(cartItemRequest.getQuantity())
                .product(Product.builder()
                        .barCodeId(cartItemRequest.getBarCodeId())
                        .build())
                .build();
    }
}
