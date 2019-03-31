package com.store.BillService.service;

import com.store.BillService.dao.CategoryRepo;
import com.store.BillService.dao.ProductRepo;
import com.store.BillService.entity.Category;
import com.store.BillService.entity.Product;
import com.store.BillService.exception.NoRecordFoundException;
import com.store.BillService.wrapper.PriceList;
import com.store.BillService.wrapper.ProductResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

@Service
public class ProductServiceImpl implements ProductService{

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public void createProduct(Product product) {
        Category category = categoryRepo.findProductByName(product.getCategory().getName());
        if(isNull(category))
            productRepo.save(product);
        else{
            product.setCategory(category);
            productRepo.save(product);
        }

    }

    @Override
    public Optional<ProductResponse> getProductByBarCodeId(String barCodeId) {
        Optional<Product> productByBarCodeId = productRepo.findProductByBarCodeId(barCodeId);
        if(!productByBarCodeId.isPresent())
            throw new NoRecordFoundException("Product not found");
        Optional<ResponseEntity<PriceList>> priceResponseEntity = Optional.empty();
        try {
            priceResponseEntity = Optional.ofNullable(restTemplate.exchange("http://localhost:8301/productPrice/price/" + barCodeId, HttpMethod.GET,
                    null, new ParameterizedTypeReference<PriceList>() {
                    }));
        }
        catch(Exception ex){
            logger.warn("Could not get price for product: "+barCodeId);
        }
        Optional <PriceList> price = priceResponseEntity.isPresent() ? Optional.ofNullable(priceResponseEntity.get().getBody()) : Optional.empty();
        Optional<ProductResponse> productWithPrice = Optional.ofNullable(ProductResponse.map(productByBarCodeId.get()));
        if(productWithPrice.isPresent())
            productWithPrice.get().setPrice(price.isPresent() ? price.get().getPrice() : null);
        return productWithPrice;
    }
}
