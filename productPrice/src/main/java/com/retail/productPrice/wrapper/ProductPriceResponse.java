package com.retail.productPrice.wrapper;

import com.retail.productPrice.entity.ProductPrice;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Data
@Builder
public class ProductPriceResponse {

    @NotNull
    private String productName;

    @NotNull
    private BigDecimal price;

    public static ProductPriceResponse map(ProductPrice productPrice){
        return ProductPriceResponse.builder()
                .productName(productPrice.getProductName())
                .price(productPrice.getPrice())
                .build();
    }
}
