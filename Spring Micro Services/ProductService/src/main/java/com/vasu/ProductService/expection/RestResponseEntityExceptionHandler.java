package com.vasu.ProductService.expection;

import com.vasu.ProductService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductException productException){

        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(productException.getMessage())
                .errorCode(productException.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
