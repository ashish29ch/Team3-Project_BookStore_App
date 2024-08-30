package com.digitInsurance.bookStoreServicesApp.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private String bookTitle;
    private Integer quantity;
    private Double price;
}
