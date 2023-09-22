package com.vasu.ProductService.expection;

import lombok.Data;

@Data
public class ProductException extends RuntimeException{

    private String errorCode;

    public ProductException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
