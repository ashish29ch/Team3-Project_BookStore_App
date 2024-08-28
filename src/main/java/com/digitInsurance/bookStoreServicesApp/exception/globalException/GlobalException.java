package com.digitInsurance.bookStoreServicesApp.exception.globalException;

import com.digitInsurance.bookStoreServicesApp.exception.customException.*;
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

    @ExceptionHandler(BookNotFound.class)
    public ResponseEntity<?> handleBookNotFound(BookNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BookAlreadyExists.class)
    public ResponseEntity<?> handleBookNotFound(BookAlreadyExists e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExist(UsernameAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(RoleNotValid.class)
    public ResponseEntity<?> handleRoleNotFound(RoleNotValid e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(InvalidLoginDetailsException.class)
    public ResponseEntity<?> handleInvalidLoginDetails(InvalidLoginDetailsException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<?> handleIn(InsufficientStockException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }
}
