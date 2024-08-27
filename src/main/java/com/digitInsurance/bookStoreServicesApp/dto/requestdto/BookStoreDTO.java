package com.digitInsurance.bookStoreServicesApp.dto.requestdto;

import lombok.Data;

@Data
public class BookStoreDTO {
    private String name;
    private String description;
    private String price;
    private Long stock;
}
