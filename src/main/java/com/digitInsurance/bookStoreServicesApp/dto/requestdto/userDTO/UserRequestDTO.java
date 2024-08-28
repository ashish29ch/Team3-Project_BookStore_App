package com.digitInsurance.bookStoreServicesApp.dto.requestdto.userDTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserRequestDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 10, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;

    @Email(message = "Invalid Email Address")
    @NotEmpty(message = "Email cannot be empty")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Email should be valid"
    )
    private String email;

    @NotEmpty(message = "Role cannot be empty")
    private String role;
}
