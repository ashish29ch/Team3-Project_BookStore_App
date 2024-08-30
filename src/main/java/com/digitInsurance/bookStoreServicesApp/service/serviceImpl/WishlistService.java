package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.responsedto.WishlistResponseDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import com.digitInsurance.bookStoreServicesApp.model.Wishlist;
import com.digitInsurance.bookStoreServicesApp.repo.BookStoreRepository;
import com.digitInsurance.bookStoreServicesApp.repo.UserRepository;
import com.digitInsurance.bookStoreServicesApp.repo.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private BookStoreRepository bookStoreRepository;

    public WishlistResponseDTO getWishlistByUser(Long userId) throws ResourceNotFoundException {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Wishlist wishlist = wishlistRepository.findByUser(user);
        return convertToDTO(wishlist);
    }

    public WishlistResponseDTO addBookToWishlist(Long userId, Long bookId) throws ResourceNotFoundException {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BookStore book = bookStoreRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Wishlist wishlist = wishlistRepository.findByUser(user);
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlist.setBooks(new ArrayList<>()); // Initialize the books list
        }
        if (wishlist.getBooks().contains(book)) {
            throw new IllegalArgumentException("Book is already in the wishlist");
        }
        wishlist.getBooks().add(book);
        wishlist = wishlistRepository.save(wishlist);
        return convertToDTO(wishlist);
    }

    public WishlistResponseDTO removeBookFromWishlist(Long userId, Long bookId) throws ResourceNotFoundException {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BookStore book = bookStoreRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Wishlist wishlist = wishlistRepository.findByUser(user);
        if (wishlist != null) {
            if (!wishlist.getBooks().contains(book)) {
                throw new IllegalArgumentException("Book is not in the wishlist");
            }
            wishlist.getBooks().remove(book);
            wishlist = wishlistRepository.save(wishlist);
            return convertToDTO(wishlist);
        }
        throw new IllegalArgumentException("Wishlist not found for user");
    }

    private WishlistResponseDTO convertToDTO(Wishlist wishlist) {
        WishlistResponseDTO wishlistDTO = new WishlistResponseDTO();
        wishlistDTO.setId(wishlist.getId());
        wishlistDTO.setBooks(wishlist.getBooks());
        return wishlistDTO;
    }
}