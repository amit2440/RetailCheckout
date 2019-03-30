package com.retail.productPrice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by P10433805 on 3/30/2019.
 */

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(ProductPriceValidationException.class)
    public ResponseEntity<ErrorResponse> handleProductPricingValidationException(HttpServletRequest req, ProductPriceValidationException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getErrrMessage())
                .errorCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductPricingValidationException(HttpServletRequest req, NoRecordFoundException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getErrrMessage())
                .errorCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
