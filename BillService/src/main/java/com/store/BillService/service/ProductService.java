package com.store.BillService.service;

import com.store.BillService.entity.Product;
import com.store.BillService.wrapper.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();
    void createProduct(Product product);
    Optional<ProductResponse> getProductByBarCodeId(String barCodeId);

}
