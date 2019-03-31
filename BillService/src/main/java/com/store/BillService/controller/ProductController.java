package com.store.BillService.controller;

import com.store.BillService.exception.NoRecordFoundException;
import com.store.BillService.service.ProductService;
import com.store.BillService.wrapper.ProductRequest;
import com.store.BillService.wrapper.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = {"/product"})
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Get product by barCodeId
     * @param barCodeId
     * @return
     */
    @GetMapping(value = {"/{barCodeId}"})
    public ResponseEntity<ProductResponse> getProductByBarCodeId(
            @PathVariable
            String barCodeId
    ){
        Optional<ProductResponse> productByBarCodeId = productService.getProductByBarCodeId(barCodeId);
        return ResponseEntity.status(HttpStatus.FOUND).body(productByBarCodeId.get());
    }

    /**
     * Add new Product
     * @param productRequest
     * @return
     */
    @PostMapping(value = {"/addProduct"})
    public ResponseEntity addProduct(
            @RequestBody
                    ProductRequest productRequest
    ){
        productService.createProduct(ProductRequest.map(productRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
