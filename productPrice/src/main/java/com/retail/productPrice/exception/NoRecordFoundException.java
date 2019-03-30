package com.retail.productPrice.exception;

import lombok.Data;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Data
public class NoRecordFoundException extends RuntimeException{

    private String errrMessage;

    public NoRecordFoundException() {
        super();
    }

    public NoRecordFoundException(String errrMessage) {
        super(errrMessage);
        this.errrMessage = errrMessage;
    }
}
