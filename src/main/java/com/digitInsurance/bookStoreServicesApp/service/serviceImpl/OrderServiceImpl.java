package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderItemRequest;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderRequest;
import com.digitInsurance.bookStoreServicesApp.model.Order;
import com.digitInsurance.bookStoreServicesApp.model.OrderItem;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import com.digitInsurance.bookStoreServicesApp.repo.OrderRepository;
import com.digitInsurance.bookStoreServicesApp.repo.UserRepository;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Users user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setShippingDetails(orderRequest.getShippingDetails());
        order.setPaymentDetails(orderRequest.getPaymentDetails());
        order.setConfirmationNumber(UUID.randomUUID().toString());
        order.setConfirmationDate(new Date());

        double totalAmount = 0;
        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(itemRequest.getBook());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(itemRequest.getPrice());
            totalAmount += itemRequest.getPrice() * itemRequest.getQuantity();
            order.getItems().add(orderItem);
        }
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }
}
