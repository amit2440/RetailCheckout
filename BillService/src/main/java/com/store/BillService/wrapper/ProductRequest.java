package com.store.BillService.wrapper;

import com.store.BillService.entity.Category;
import com.store.BillService.entity.Product;
import com.store.BillService.utils.CategoryType;
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
    private String category;

    public static Product map (ProductRequest request){
        CategoryType categoryType = CategoryType.valueOf(request.category);
        return Product.builder()
                .name(request.getName())
                .barCodeId(request.getBarCodeId())
                .category(Category.builder()
                        .name(request.category)
                        .salesTax(new BigDecimal(categoryType.getCategoryTypeValue()))
                .build())
                .build();
    }
}
