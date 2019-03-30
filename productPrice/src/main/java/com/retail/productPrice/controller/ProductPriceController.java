package com.retail.productPrice.controller;

import com.retail.productPrice.entity.ProductPrice;
import com.retail.productPrice.service.ProductPriceService;
import com.retail.productPrice.wrapper.ProductPriceRequest;
import com.retail.productPrice.wrapper.ProductPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Controller
@RequestMapping(value = {"/productPrice"})
public class ProductPriceController {

    @Autowired
    private ProductPriceService productPriceService;

    @GetMapping(value = {"/price/{productName}"})
    public ResponseEntity<ProductPriceResponse> getProductPrice(
            @PathVariable(value = "productName")
            String productName
    ){
        ProductPrice productPrice = productPriceService.getProductPrice(productName);
        return ResponseEntity.status(HttpStatus.FOUND).body(ProductPriceResponse.map(productPrice));
    }

    @PostMapping(value = {"/addProductPrice"})
    public ResponseEntity createProductPrice(
            @Valid
            @RequestBody
            ProductPriceRequest request
    ){
        productPriceService.createProductPrice(ProductPriceRequest.map(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = {"/allProductPrices"})
    public ResponseEntity<List<ProductPriceResponse>> getAllProductPrice(){
        List<ProductPrice> allProductPrice = productPriceService.getAllProductPrice();
        List<ProductPriceResponse> productPriceResponseList = allProductPrice.stream().map(productPrice -> ProductPriceResponse.map(productPrice)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.FOUND).body(productPriceResponseList);
    }

    @PutMapping(value = {"/updateProductPrice"})
    public ResponseEntity updateProductPrice(
            @Valid
            @RequestBody
            ProductPriceRequest request
    ){
        productPriceService.updateProductPrice(ProductPriceRequest.map(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
