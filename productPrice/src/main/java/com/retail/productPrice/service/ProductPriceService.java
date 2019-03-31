package com.retail.productPrice.service;

import com.retail.productPrice.entity.ProductPrice;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by P10433805 on 3/30/2019.
 */

public interface ProductPriceService {

    ProductPrice getProductPrice(String barCodeId);
    List<ProductPrice> getAllProductPrice();
    void updateProductPrice(ProductPrice productPrice);
    void createProductPrice(ProductPrice productPrice);
}
