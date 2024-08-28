package com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class BookUpdateDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;
    private String price;
    private Long stock;
}
