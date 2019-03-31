package com.store.BillService.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.store.BillService.entity.Bill;
import com.store.BillService.entity.CartItem;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillResponse {

    private Long billId;

    private Set<CartItemResponse> cartItems;

    private BigDecimal totalSalesTax;

    private BigDecimal totalCost;

    private BigDecimal totalBillAmount;

    public static BillResponse map (Bill bill){
        return BillResponse.builder()
                .billId(bill.getId())
                .totalSalesTax(bill.getTotalSalesTax())
                .totalCost(bill.getTotalCost())
                .totalBillAmount(bill.getTotalSalesTax().add(bill.getTotalCost()))
                .build();
    }
}
