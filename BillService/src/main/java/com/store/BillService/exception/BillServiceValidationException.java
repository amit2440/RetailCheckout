package com.store.BillService.exception;

import lombok.Data;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Data
public class BillServiceValidationException extends RuntimeException{

    private String errMessage;

    public BillServiceValidationException() {
        super();
    }

    public BillServiceValidationException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }
}
