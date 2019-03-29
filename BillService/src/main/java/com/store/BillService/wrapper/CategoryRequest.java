package com.store.BillService.wrapper;

import com.store.BillService.entity.Category;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CategoryRequest {

    @NotNull
    private String name;

    @NotNull
    private BigDecimal salesTax;

    public static Category map(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.getName())
                .salesTax(categoryRequest.getSalesTax())
                .build();
    }
}
