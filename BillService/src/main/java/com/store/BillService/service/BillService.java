package com.store.BillService.service;

import com.store.BillService.entity.Bill;
import com.store.BillService.wrapper.BillResponse;

public interface BillService {

    BillResponse getBillById(Long billId);
    Long createBill(Bill bill);
}
