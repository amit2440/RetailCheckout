package com.store.BillService.controller;

import com.store.BillService.service.CategoryService;
import com.store.BillService.wrapper.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/category"})
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Create category for products
     * @param categoryRequest
     * @return
     */
    @PostMapping(value = {"/createCategory"})
    public ResponseEntity createCategory(
                @RequestBody
                CategoryRequest categoryRequest
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryService.createCategory(CategoryRequest.map(categoryRequest))
        );
    }
}
