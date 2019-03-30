package com.retail.productPrice.wrapper;

import com.retail.productPrice.entity.ProductPrice;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Data
public class ProductPriceRequest {

    @NotNull(message = "Product Name cannot be null")
    private String productName;

    @NotNull(message = "Product Price cannot be null")
    private BigDecimal price;

    public static ProductPrice map(ProductPriceRequest request){
        return ProductPrice.builder()
                .productName(request.getProductName())
                .price(request.getPrice())
                .build();
    }
}
