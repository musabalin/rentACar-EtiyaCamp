package com.etiya.rentACar.core.crossCuttingConserns.exceptionHandling;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
