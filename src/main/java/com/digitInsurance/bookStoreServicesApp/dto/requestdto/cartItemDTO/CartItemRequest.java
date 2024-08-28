package com.digitInsurance.bookStoreServicesApp.dto.requestdto.cartItemDTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartItemRequest {
    private int quantity;
}
