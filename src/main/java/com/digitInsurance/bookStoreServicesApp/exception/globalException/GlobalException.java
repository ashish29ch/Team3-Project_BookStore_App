package com.digitInsurance.bookStoreServicesApp.exception.globalException;

import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<?> handleResourceNotFoundException(TokenNotValidException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
