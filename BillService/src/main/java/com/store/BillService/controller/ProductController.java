package com.store.BillService.controller;

import com.store.BillService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/product"})
public class ProductController {

    @Autowired
    private ProductService productService;

    public List<Produc>
}
