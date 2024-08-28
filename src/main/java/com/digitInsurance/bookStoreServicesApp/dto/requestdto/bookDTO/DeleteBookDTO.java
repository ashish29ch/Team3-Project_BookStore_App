package com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteBookDTO {

    @NotBlank(message = "Name is mandatory")
    String name;
}
