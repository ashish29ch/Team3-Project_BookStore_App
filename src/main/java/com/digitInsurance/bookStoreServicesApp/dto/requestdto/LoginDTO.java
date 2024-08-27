package com.digitInsurance.bookStoreServicesApp.dto.requestdto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginDTO {
    private String userName;
    private String password;
}
