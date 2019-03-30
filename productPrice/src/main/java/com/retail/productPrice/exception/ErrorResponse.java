package com.retail.productPrice.exception;

import lombok.Builder;
import lombok.Data;

/**
 * Created by P10433805 on 3/30/2019.
 */

@Data
@Builder
public class ErrorResponse {

    private int errorCode;
    private String field;
    private String message;

}
