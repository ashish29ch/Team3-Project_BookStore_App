package com.digitInsurance.bookStoreServicesApp.exception.customException;

public class UsernameAlreadyExistException extends Exception {
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
