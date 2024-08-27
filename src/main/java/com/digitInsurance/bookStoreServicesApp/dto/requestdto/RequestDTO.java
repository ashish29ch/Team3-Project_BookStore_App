package com.digitInsurance.bookStoreServicesApp.dto.requestdto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RequestDTO {

    private String username;
    private String password;
    private String email;
}
