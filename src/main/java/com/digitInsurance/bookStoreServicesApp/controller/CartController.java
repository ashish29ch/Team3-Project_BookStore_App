package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.exception.customException.InsufficientStockException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.Cart;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.CartService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

        @Autowired
        private CartService cartService;

        @PostMapping("/add/book/{bookId}")
        public ResponseEntity<Cart> addItemToCart(
                @RequestHeader("Authorization") String token,
                @PathVariable Long bookId) throws TokenNotValidException, ResourceNotFoundException {

            Long userId = JWTToken.getUserIdFromToken(token);
            Cart cart = cartService.addItemToCart(userId, bookId);
            return ResponseEntity.ok(cart);
        }

    @GetMapping
    public ResponseEntity<?> getCartByUserId(@RequestHeader("Authorization") String token) {
        try {
            Long userId = JWTToken.getUserIdFromToken(token);
            Cart cart = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(cart);
        } catch (TokenNotValidException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    @DeleteMapping("/delete/book/{bookId}")
    public ResponseEntity<?> removeItemFromCart(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        try {
            Long userId = JWTToken.getUserIdFromToken(token);
            cartService.removeItemFromCart(userId, bookId);
            return ResponseEntity.ok("Item removed from cart");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Item not present in cart");
        }
    }



    @PutMapping("/increase/quantity/{bookId}")
    public ResponseEntity<?> increaseItemQuantity(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        try {
            Long userId = JWTToken.getUserIdFromToken(token);
            Cart updatedCart = cartService.increaseItemQuantity(userId, bookId);
            return ResponseEntity.ok(updatedCart);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InsufficientStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PutMapping("/decrease/quantity/{bookId}")
    public ResponseEntity<?> decreaseItemQuantity(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        try {
            Long userId = JWTToken.getUserIdFromToken(token);
            Cart updatedCart = cartService.decreaseItemQuantity(userId, bookId);
            return ResponseEntity.ok(updatedCart);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
