package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO.OrderRequest;
import com.digitInsurance.bookStoreServicesApp.model.Order;

public interface OrderService {
    public Order placeOrder(OrderRequest orderRequest);
}
