package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.responsedto.WishlistResponseDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;

public interface WishlistService {

    public WishlistResponseDTO getWishlistByUser(Long userId) throws ResourceNotFoundException;

    public WishlistResponseDTO addBookToWishlist(Long userId, Long bookId) throws ResourceNotFoundException;

    public WishlistResponseDTO removeBookFromWishlist(Long userId, Long bookId) throws ResourceNotFoundException;
}
