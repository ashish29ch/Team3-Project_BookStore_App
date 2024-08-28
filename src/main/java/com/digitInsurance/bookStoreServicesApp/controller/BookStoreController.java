package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookStoreDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookUpdateDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.DeleteBookDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.BookAlreadyExists;
import com.digitInsurance.bookStoreServicesApp.exception.customException.BookNotFound;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.RoleName;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.BookStoreService;
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

    @PutMapping("/update/book")
    public ResponseEntity<?> updateBook(@RequestHeader("Authorization") String token,@Valid @RequestBody BookUpdateDTO bookUpdateDTO) throws TokenNotValidException, BookNotFound {
        String role=JWTToken.getRoleFromToken(token);
        if(role.equals(String.valueOf(RoleName.ROLE_ADMIN))){
            return ResponseEntity.status(HttpStatus.OK).body(bookStoreService.updateBook(bookUpdateDTO));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not allowed to update products");
        }
    }

    @PostMapping("/add/book")
    public ResponseEntity<?> addBook(@RequestHeader("Authorization") String token,@Valid @RequestBody BookStoreDTO bookStoreDTO) throws TokenNotValidException, BookAlreadyExists {
        String role = JWTToken.getRoleFromToken(token);
        if(role.equals(String.valueOf(RoleName.ROLE_ADMIN))){
            return ResponseEntity.status(HttpStatus.OK).body(bookStoreService.addBook(bookStoreDTO));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not an Admin");
        }

    }
    @DeleteMapping("/delete/book")
    public ResponseEntity<?> deleteBook(@RequestHeader("Authorization") String token, @Valid @RequestBody DeleteBookDTO deleteBookDTO) throws TokenNotValidException,BookNotFound{
        String role=JWTToken.getRoleFromToken(token);
        if(role.equals(String.valueOf(RoleName.ROLE_ADMIN))){
            return ResponseEntity.status(HttpStatus.OK).body(bookStoreService.deleteBook(deleteBookDTO).getBody());
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You cannot delete a Product");
        }
    }
}
