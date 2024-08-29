package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.exception.customException.InsufficientStockException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.model.Cart;

public interface CartService {
    public Cart addItemToCart(Long userId, Long bookId) throws ResourceNotFoundException;

    public Cart getCartByUserId(Long userId) throws ResourceNotFoundException;

    public void removeItemFromCart(Long userId, Long bookId) throws ResourceNotFoundException;

    Cart increaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException, InsufficientStockException;
    Cart decreaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException;
}
