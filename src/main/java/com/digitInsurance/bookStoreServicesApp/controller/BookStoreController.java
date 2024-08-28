package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookStoreDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookUpdateDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.DeleteBookDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.BookAlreadyExists;
import com.digitInsurance.bookStoreServicesApp.exception.customException.BookNotFound;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import com.digitInsurance.bookStoreServicesApp.model.RoleName;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.BookStoreService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore")
@Validated
public class BookStoreController {
    @Autowired
    BookStoreService bookStoreService;

    @PutMapping("/update/book")
    public ResponseEntity<?> updateBook(@RequestHeader("Authorization") String token,@Valid @RequestBody BookUpdateDTO bookUpdateDTO) throws TokenNotValidException, BookNotFound {
        String role=JWTToken.getRoleFromToken(token);
        if(role.equals(String.valueOf(RoleName.ROLE_USER))){
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

    @GetMapping("/get/book/all")
    public ResponseEntity<?> getAllBooksList() throws TokenNotValidException {

            Optional<List<BookStore>> booksList = bookStoreService.getAllBooksList();

            if (booksList.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(booksList.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No books found");
            }
    }


    @GetMapping("/get/book/by/{id}")
    public ResponseEntity<?> getBookById(@RequestHeader("Authorization") String token ,@PathVariable Long id) throws TokenNotValidException {

        String role = JWTToken.getRoleFromToken(token);

        if (role.equals(String.valueOf(RoleName.ROLE_USER))) {

            Optional<BookStore> book = bookStoreService.getBookById(id);
            if (book.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(book.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }
}
