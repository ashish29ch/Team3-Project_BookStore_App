package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderRequest;
import com.digitInsurance.bookStoreServicesApp.model.Order;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(order);
    }
}
