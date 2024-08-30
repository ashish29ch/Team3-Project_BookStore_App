package com.digitInsurance.bookStoreServicesApp.repo;

import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import com.digitInsurance.bookStoreServicesApp.model.Order;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser(Users user);

    boolean existsByUserAndItemsBook(Users user, BookStore book);

    List<Order> findByUserId(Long id);
}
