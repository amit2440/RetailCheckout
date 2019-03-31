package com.store.BillService.exception;

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

    @ExceptionHandler(BillServiceValidationException.class)
    public ResponseEntity<ErrorResponse> handleBillServiceValidationException(HttpServletRequest req, BillServiceValidationException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getErrMessage())
                .errorCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ErrorResponse> handleBillServiceValidationException(HttpServletRequest req, NoRecordFoundException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getErrMessage())
                .errorCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
