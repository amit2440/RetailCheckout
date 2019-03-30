package com.store.BillService.wrapper;

import com.store.BillService.entity.Category;
import com.store.BillService.entity.Product;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotNull
    private String name;

    @NotNull
    private String barCodeId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String category;

    public static Product map (ProductRequest request){
        return Product.builder()
                .name(request.getName())
                .barCodeId(request.getBarCodeId())
                .price(request.getPrice())
                .category(Category.builder()
                        .name(request.category)
                .build())
                .build();
    }
}
