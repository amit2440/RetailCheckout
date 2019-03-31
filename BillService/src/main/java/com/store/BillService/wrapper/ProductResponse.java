package com.store.BillService.wrapper;

import com.store.BillService.entity.Category;
import com.store.BillService.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String barCodeId;
    private String category;
    private BigDecimal price;
    private BigDecimal salesTax;

    public static ProductResponse map (Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .barCodeId(product.getBarCodeId())
                .category(product.getCategory().getName())
                .salesTax(product.getCategory().getSalesTax())
                .build();
    }
}
