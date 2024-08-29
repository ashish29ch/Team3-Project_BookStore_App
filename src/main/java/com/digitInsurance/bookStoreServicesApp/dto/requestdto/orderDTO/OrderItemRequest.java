package com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO;


import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private BookStore book;
    private Integer quantity;
    private Double price;
}

