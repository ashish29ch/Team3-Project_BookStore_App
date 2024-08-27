package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.BookStoreDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.RoleName;
import com.digitInsurance.bookStoreServicesApp.serviceInterfaces.BookStoreService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore")
@Validated
public class BookStoreController {
    @Autowired
    BookStoreService bookStoreService;

    @PostMapping("/add/book")
    public ResponseEntity<?> addBook(@RequestHeader("Authorization") String token,@Valid @RequestBody BookStoreDTO bookStoreDTO) throws TokenNotValidException {
        String role = JWTToken.getRoleFromToken(token);
        if(role.equals(String.valueOf(RoleName.ROLE_ADMIN))){
            return ResponseEntity.status(HttpStatus.OK).body(bookStoreService.addBook(bookStoreDTO));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not an Admin");
        }

    }
}
