package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.responsedto.WishlistResponseDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.service.serviceImpl.WishlistServiceImpl;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore/wishlist")
public class WishlistController {

    @Autowired
    private WishlistServiceImpl wishlistServiceImpl;

    @GetMapping("/get")
    public WishlistResponseDTO getWishlist(@RequestHeader("Authorization") String token) throws ResourceNotFoundException, TokenNotValidException {
        Long userId = JWTToken.getUserIdFromToken(token);
        return wishlistServiceImpl.getWishlistByUser(userId);
    }

    @PostMapping("/add/book/{bookId}")
    public ResponseEntity<WishlistResponseDTO> addBookToWishlist(
            @RequestHeader("Authorization") String token,
            @PathVariable Long bookId) throws TokenNotValidException, ResourceNotFoundException {

        Long userId = JWTToken.getUserIdFromToken(token);
        WishlistResponseDTO wishlist = wishlistServiceImpl.addBookToWishlist(userId, bookId);
        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/remove/book/{bookId}")
    public ResponseEntity<WishlistResponseDTO> removeBookFromWishlist(
            @RequestHeader("Authorization") String token,
            @PathVariable Long bookId) throws TokenNotValidException, ResourceNotFoundException {

        Long userId = JWTToken.getUserIdFromToken(token);
        WishlistResponseDTO wishlist = wishlistServiceImpl.removeBookFromWishlist(userId, bookId);
        return ResponseEntity.ok(wishlist);
    }
}
