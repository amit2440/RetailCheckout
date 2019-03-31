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

    /**
     * Get price for product
     * @param barCodeId
     * @return
     */
    @GetMapping(value = {"/price/{barCodeId}"})
    public ResponseEntity<ProductPriceResponse> getProductPrice(
            @PathVariable(value = "barCodeId")
            String barCodeId
    ){
        ProductPrice productPrice = productPriceService.getProductPrice(barCodeId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ProductPriceResponse.map(productPrice));
    }

    /**
     * Add price for product
     * @param request
     * @return
     */
    @PostMapping(value = {"/addProductPrice"})
    public ResponseEntity createProductPrice(
            @Valid
            @RequestBody
            ProductPriceRequest request
    ){
        productPriceService.createProductPrice(ProductPriceRequest.map(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Get list of all product prices
     * @return
     */
    @GetMapping(value = {"/allProductPrices"})
    public ResponseEntity<List<ProductPriceResponse>> getAllProductPrice(){
        List<ProductPrice> allProductPrice = productPriceService.getAllProductPrice();
        List<ProductPriceResponse> productPriceResponseList = allProductPrice.stream().map(productPrice -> ProductPriceResponse.map(productPrice)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.FOUND).body(productPriceResponseList);
    }

    /**
     * Update price of a product
     * @param request
     * @return
     */
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
