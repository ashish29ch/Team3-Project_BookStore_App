package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.exception.customException.InsufficientStockException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import com.digitInsurance.bookStoreServicesApp.model.Cart;
import com.digitInsurance.bookStoreServicesApp.model.CartItem;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import com.digitInsurance.bookStoreServicesApp.repo.BookStoreRepository;
import com.digitInsurance.bookStoreServicesApp.repo.CartItemRepository;
import com.digitInsurance.bookStoreServicesApp.repo.CartRepository;
import com.digitInsurance.bookStoreServicesApp.repo.UserRepository;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public Cart addItemToCart(Long userId, Long bookId) throws ResourceNotFoundException {
        Optional<Users> userOptional = usersRepository.findById(userId);
        Optional<BookStore> bookOptional = bookStoreRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            Users user = userOptional.get();
            BookStore book = bookOptional.get();

            if (book.getStock() < 1) {
                throw new ResourceNotFoundException("Not enough stock available");
            }

            Cart cart = cartRepository.findByUser(user)
                    .orElse(new Cart());
            cart.setUser(user);

            // Save the cart if it's new
            if (cart.getId() == null) {
                cart = cartRepository.save(cart);
            }

            CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book)
                    .orElse(new CartItem(cart, book, 0, 0.0));

            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setPrice(Double.valueOf(book.getPrice()));

            book.setStock(book.getStock() - 1);

            cartItemRepository.save(cartItem);
            bookStoreRepository.save(book);

            cart.calculateTotalPrice();
            cartRepository.save(cart);

            return cart;
        } else {
            throw new ResourceNotFoundException("User or Book not found");
        }
    }

    @Override
    public Cart getCartByUserId(Long userId) throws ResourceNotFoundException {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }
}
