package com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginDTO {
    private String username;
    private String password;
}
