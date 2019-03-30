package com.store.BillService.service;

import com.store.BillService.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    void createProduct(Product product);

}
