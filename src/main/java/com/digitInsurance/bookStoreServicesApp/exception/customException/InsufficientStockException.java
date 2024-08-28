package com.digitInsurance.bookStoreServicesApp.exception.customException;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
