package com.digitInsurance.bookStoreServicesApp.exception.customException;

public class BookAlreadyExists  extends Exception{
    public BookAlreadyExists(String message) {
        super(message);
    }
}
