package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderRequest;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.Order;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.OrderService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestHeader("Authorization") String token,
                                            @RequestBody OrderRequest orderRequestDTO) throws TokenNotValidException, ResourceNotFoundException {
        Long userId = JWTToken.getUserIdFromToken(token);
        Order order = orderService.placeOrder(userId, orderRequestDTO);
        return ResponseEntity.ok(order);
    }


    @GetMapping("/history")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String token) throws TokenNotValidException, ResourceNotFoundException {
        Long userId = JWTToken.getUserIdFromToken(token);
        List<Order> orderHistory = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(orderHistory);
    }
}
