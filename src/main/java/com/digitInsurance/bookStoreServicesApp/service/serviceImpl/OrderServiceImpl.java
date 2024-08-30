package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderRequest;
import com.digitInsurance.bookStoreServicesApp.dto.responsedto.OrderDTO;
import com.digitInsurance.bookStoreServicesApp.dto.responsedto.OrderItemDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.model.*;
import com.digitInsurance.bookStoreServicesApp.repo.*;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.OrderService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Override
    @Transactional
    public Order placeOrder(Long userId, OrderRequest orderRequest) throws ResourceNotFoundException {
        Optional<Users> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            Cart cart = cartRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

            if (cart.getItems().isEmpty()) {
                throw new ResourceNotFoundException("Cart is empty");
            }

            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(new Date());
            order.setPaymentDetails(orderRequest.getPaymentDetails());

            // Set the address for the order
            if (!user.getAddress().isEmpty()) {
                order.setAddress(user.getAddress().get(0)); // Use the first address
            } else {
                throw new ResourceNotFoundException("User has no address");
            }

            List<OrderItem> orderItems = new ArrayList<>();

            for (CartItem cartItem : cart.getItems()) {
                BookStore book = cartItem.getBook();
                OrderItem orderItem = new OrderItem(order, book, cartItem.getQuantity(), cartItem.getPrice());
                orderItems.add(orderItem);
            }

            order.setItems(orderItems);
            order.calculateTotalAmount();

            Order newOrder = orderRepository.save(order);

            // Clear the cart
            cartItemRepository.deleteAll(cart.getItems());
            cart.getItems().clear();
            cart.setTotalPrice(0.0); // Reset the total price to zero
            cartRepository.save(cart);

            return newOrder;
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public List<Order> getOrderHistory(Long userId) throws ResourceNotFoundException {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Order> orders = orderRepository.findByUser(user);
        for (Order order : orders) {
            Hibernate.initialize(order.getItems());
            for (OrderItem item : order.getItems()) {
                Hibernate.initialize(item.getBook());
            }
        }
        return orders;
    }

    public static OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> orderItemDTOs = order.getItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        item.getBook().getName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getTotalAmount(),
                orderItemDTOs
        );
    }
}
