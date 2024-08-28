package com.digitInsurance.bookStoreServicesApp.repo;

import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import com.digitInsurance.bookStoreServicesApp.model.Cart;
import com.digitInsurance.bookStoreServicesApp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndBook(Cart cart, BookStore book);
}
