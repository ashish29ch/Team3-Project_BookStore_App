package com.digitInsurance.bookStoreServicesApp.dto.requestdto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserUpdateDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Email(message = "Invalid Email Address")
    @NotEmpty(message = "Email cannot be empty")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Email should be valid"
    )
    private String email;
}
