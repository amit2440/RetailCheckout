package com.store.BillService.service;

import com.netflix.discovery.converters.Auto;
import com.store.BillService.dao.BillRepo;
import com.store.BillService.dao.CartItemRepo;
import com.store.BillService.entity.Bill;
import com.store.BillService.entity.CartItem;
import com.store.BillService.entity.Product;
import com.store.BillService.exception.BillServiceValidationException;
import com.store.BillService.exception.NoRecordFoundException;
import com.store.BillService.wrapper.BillResponse;
import com.store.BillService.wrapper.CartItemRequest;
import com.store.BillService.wrapper.CartItemResponse;
import com.store.BillService.wrapper.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepo billRepo;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemRepo cartItemRepo;

    @Override
    public BillResponse getBillById(Long billId) {
        Optional<Bill> bill = billRepo.findById(billId);
        if(!bill.isPresent())
            throw new NoRecordFoundException("Bill not found: "+billId);
        List<CartItem> listOfCartItemsForBillId = cartItemService.getListOfCartItemsForBillId(billId);
        if(listOfCartItemsForBillId.isEmpty())
            throw new NoRecordFoundException("No items found for bill id: "+billId);

        BillResponse billDetails = BillResponse.map(bill.get());
        billDetails.setCartItems(CartItemResponse.map(listOfCartItemsForBillId));
        return billDetails;
    }

    @Override
    public Long createBill(Bill bill) {
        bill.getCartItems().stream().forEach(cartItem -> {
            prepareCartItem(cartItem);
        });
        prepareBill(bill);
        return billRepo.save(bill).getId();
    }

    private void prepareBill(Bill bill) {
        List<BigDecimal> listofPrices = bill.getCartItems()
                .stream()
                .map(item -> item.getPurchaseCost()).collect(Collectors.toList());
        bill.setTotalCost(listofPrices.stream().reduce(BigDecimal.ZERO,BigDecimal::add));
        List<BigDecimal> itemSalesTaxList = bill.getCartItems()
                .stream()
                .map(item -> item.getPurchaseSalesTax()).collect(Collectors.toList());
        bill.setTotalSalesTax(itemSalesTaxList.stream().reduce(BigDecimal.ZERO,BigDecimal::add));
    }

    private void prepareCartItem(CartItem cartItem) {
        Optional<ProductResponse> product = productService.getProductByBarCodeId(cartItem.getProduct().getBarCodeId());
        if (!product.isPresent())
            throw new NoRecordFoundException("Product not found");
        // Could not run 2 services on same machine , so hardcoded price for testing
       // product.get().setPrice(new BigDecimal(500));
        if (isNull(product.get().getPrice()))
            throw new NoRecordFoundException("No price present for product: " + cartItem.getProduct().getBarCodeId());
        cartItem.setProduct(Product.builder()
                .id(product.get().getId())
                .build());
        cartItem.setPurchaseCost(product.get().getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        cartItem.setPurchaseSalesTax((product.get().getPrice().multiply(product.get().getSalesTax().divide(new BigDecimal(100)))).multiply(new BigDecimal(cartItem.getQuantity())));
    }
}
