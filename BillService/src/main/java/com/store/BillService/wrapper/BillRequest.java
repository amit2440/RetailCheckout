package com.store.BillService.wrapper;

import com.store.BillService.entity.Bill;
import com.store.BillService.entity.CartItem;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class BillRequest {

    @NotNull
    private List<CartItemRequest> cartItems;

    public static Bill map(BillRequest billRequest){
        Set<CartItem> cartItems = new HashSet<>();
        billRequest.getCartItems().forEach(request->{
            cartItems.add(CartItemRequest.map(request));
        });
        return Bill.builder()
                .cartItems(cartItems)
                .build();
    }

}
