package com.retail.productPrice.service;

import com.retail.productPrice.dao.ProductPriceDao;
import com.retail.productPrice.entity.ProductPrice;
import com.retail.productPrice.exception.NoRecordFoundException;
import com.retail.productPrice.exception.ProductPriceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Service
public class ProductPriceServiceImpl  implements ProductPriceService {

    @Autowired
    private ProductPriceDao productPriceDao;

    @Override
    public ProductPrice getProductPrice(String name) {
        Optional<ProductPrice> productPrice = Optional.ofNullable(productPriceDao.findPriceByName(name));
        if(!productPrice.isPresent())
            throw new NoRecordFoundException("Product not found");
        return productPrice.get();
    }

    @Override
    public List<ProductPrice> getAllProductPrice() {
        return productPriceDao.findAll();
    }

    @Transactional
    @Override
    public void updateProductPrice(ProductPrice productPrice) {
        Optional<ProductPrice> productPrice1 = findPriceByName(productPrice);
        if(!productPrice1.isPresent())
            throw new ProductPriceValidationException("Product does not exist");
        productPriceDao.updatePrice(productPrice.getPrice(),productPrice1.get().getId());
    }

    @Override
    public void createProductPrice(ProductPrice productPrice) {
        if(findPriceByName(productPrice).isPresent())
            throw new ProductPriceValidationException("Product already exists");
        productPriceDao.save(productPrice);
    }

    private Optional<ProductPrice> findPriceByName(ProductPrice productPrice) {
        return Optional.ofNullable(productPriceDao.findPriceByName(productPrice.getProductName()));
    }
}
