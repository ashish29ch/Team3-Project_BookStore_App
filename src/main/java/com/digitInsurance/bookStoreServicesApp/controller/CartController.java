package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.Cart;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.CartService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

        @Autowired
        private CartService cartService;

        // Endpoint to add an item to the cart
        @PostMapping("/add/book/{bookId}")
        public ResponseEntity<Cart> addItemToCart(
                @RequestHeader("Authorization") String token,
                @PathVariable Long bookId) throws TokenNotValidException, ResourceNotFoundException {

            Long userId = JWTToken.getUserIdFromToken(token);
            Cart cart = cartService.addItemToCart(userId, bookId);
            return ResponseEntity.ok(cart);
        }

        // Endpoint to get the cart details for a user
        @GetMapping
        public Cart getCartByUserId(@RequestHeader("Authorization") String token) throws TokenNotValidException, ResourceNotFoundException {
            Long userId = JWTToken.getUserIdFromToken(token);
            Cart cart = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(cart).getBody();
        }
}
