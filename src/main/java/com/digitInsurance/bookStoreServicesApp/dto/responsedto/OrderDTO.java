package com.digitInsurance.bookStoreServicesApp.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Date orderDate;
    private Double totalAmount;
    private List<OrderItemDTO> items;
    // Add other fields as needed
}
