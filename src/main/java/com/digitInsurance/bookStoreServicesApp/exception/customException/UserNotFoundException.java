package com.digitInsurance.bookStoreServicesApp.exception.customException;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
