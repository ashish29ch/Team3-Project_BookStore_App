package com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookStoreDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Price is mandatory")
    @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "Price must be a valid number with up to two decimal places")
    private String price;

    @NotNull(message = "Stock is mandatory")
    @Positive(message = "Stock must be a positive number")
    private Long stock;
}
