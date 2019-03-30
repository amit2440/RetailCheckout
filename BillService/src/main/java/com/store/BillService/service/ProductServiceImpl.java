package com.store.BillService.service;

import com.store.BillService.dao.ProductRepo;
import com.store.BillService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public void createProduct(Product product) {
        productRepo.save(product);
    }
}
