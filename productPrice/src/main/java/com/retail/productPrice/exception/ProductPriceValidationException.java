package com.retail.productPrice.exception;

import lombok.Data;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Data
public class ProductPriceValidationException extends RuntimeException{

    private String errrMessage;

    public ProductPriceValidationException() {
        super();
    }

    public ProductPriceValidationException(String errrMessage) {
        super(errrMessage);
        this.errrMessage = errrMessage;
    }
}
