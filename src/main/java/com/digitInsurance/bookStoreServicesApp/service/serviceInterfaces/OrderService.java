package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderRequest;
import com.digitInsurance.bookStoreServicesApp.exception.customException.ResourceNotFoundException;
import com.digitInsurance.bookStoreServicesApp.model.Order;

import java.util.List;

public interface OrderService {
    public Order placeOrder(Long userId, OrderRequest orderRequest) throws ResourceNotFoundException;
    public List<Order> getOrderHistory(Long userId) throws ResourceNotFoundException;
}
