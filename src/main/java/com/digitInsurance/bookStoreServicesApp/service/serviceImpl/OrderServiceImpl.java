package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderItemRequest;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderRequest;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.model.*;
import com.digitInsurance.bookStoreServicesApp.repo.*;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
            //order.setStatus("PENDING");
            //order.setShippingDetails(orderRequest.getShippingDetails());
            order.setPaymentDetails(orderRequest.getPaymentDetails());

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
            List<CartItem> cartItems = new ArrayList<>(cart.getItems());
            cartItemRepository.deleteAll(cartItems);
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
        return orderRepository.findByUser(user);
    }
}
