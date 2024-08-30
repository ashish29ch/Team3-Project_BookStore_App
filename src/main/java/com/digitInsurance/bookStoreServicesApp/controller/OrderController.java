package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderRequest;
import com.digitInsurance.bookStoreServicesApp.dto.responsedto.OrderDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.Order;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.OrderService;
import com.digitInsurance.bookStoreServicesApp.service.serviceImpl.OrderServiceImpl;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/history")
    public ResponseEntity<?> getOrderHistory(@RequestHeader("Authorization") String token) throws TokenNotValidException, ResourceNotFoundException {
        Long userId = JWTToken.getUserIdFromToken(token);
        List<OrderDTO> orderHistory = orderService.getOrderHistory(userId).stream()
                .map(OrderServiceImpl::convertToDTO)
                .collect(Collectors.toList());

        if (orderHistory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No orders found for this user.");
        }
        return ResponseEntity.ok(orderHistory);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestHeader("Authorization") String token,
                                        @RequestBody OrderRequest orderRequestDTO) throws TokenNotValidException, ResourceNotFoundException {
        Long userId = JWTToken.getUserIdFromToken(token);
        try {
            Order order = orderService.placeOrder(userId, orderRequestDTO);
            return ResponseEntity.ok(order);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
