package com.store.BillService.service;

import com.store.BillService.dao.CartItemRepo;
import com.store.BillService.entity.CartItem;
import com.store.BillService.entity.Product;
import com.store.BillService.exception.BillServiceValidationException;
import com.store.BillService.exception.NoRecordFoundException;
import com.store.BillService.wrapper.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class CartItemServiceImpl implements CartItemService {


    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private BillService billService;

    @Override
    public void addToCart(CartItem cartItem) {
        Optional<ProductResponse> product = productService.getProductByBarCodeId(cartItem.getProduct().getBarCodeId());
        if(!product.isPresent())
            throw new NoRecordFoundException("Product not found");
        if(isNull(product.get().getPrice()))
            throw new NoRecordFoundException("No price found for product: "+cartItem.getProduct().getBarCodeId());
        cartItem.setProduct(Product.builder()
                .id(product.get().getId())
                .build());
        cartItem.setPurchaseCost(product.get().getPrice());
        cartItem.setPurchaseSalesTax(product.get().getPrice().multiply(product.get().getSalesTax().divide(new BigDecimal(100))));
        cartItemRepo.save(cartItem);
    }

    @Override
    public List<CartItem> getListOfCartItemsForBillId(Long id) {
        return cartItemRepo.getListOfCartItemsForBillId(id);
    }
}
