package com.store.BillService.controller;

import com.store.BillService.entity.Bill;
import com.store.BillService.service.BillService;
import com.store.BillService.wrapper.BillRequest;
import com.store.BillService.wrapper.BillResponse;
import com.store.BillService.wrapper.CartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/bill"})
public class BillControler {

    @Autowired
    BillService billService;

    /**
     * Get bill details by bill id
     * @param billId
     * @return
     */
    @GetMapping(value = {"/getBill/{billId}"})
    public ResponseEntity<BillResponse> getBillById(@PathVariable Long billId){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(billService.getBillById(billId));
    }

    /**
     * Create Bill for cart Items
     * @param billRequest
     * @return
     */
    @PostMapping(value = {"/createBill"})
    public ResponseEntity<BillResponse> createBill(
            @RequestBody
            @Valid BillRequest billRequest
    ){
        Long billId = billService.createBill(BillRequest.map(billRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(BillResponse.builder().billId(billId).build());
    }
}
