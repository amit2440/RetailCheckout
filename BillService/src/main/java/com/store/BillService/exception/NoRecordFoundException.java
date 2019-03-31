package com.store.BillService.exception;

import lombok.Data;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Data
public class NoRecordFoundException extends RuntimeException{

    private String errMessage;

    public NoRecordFoundException() {
        super();
    }

    public NoRecordFoundException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }
}
