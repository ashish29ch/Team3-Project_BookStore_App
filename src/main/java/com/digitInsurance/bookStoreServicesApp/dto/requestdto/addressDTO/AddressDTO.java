package com.digitInsurance.bookStoreServicesApp.dto.requestdto.addressDTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AddressDTO {
    private String fullName;
    private String mobileNumber;
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    private Long pincode;
    private String city;
    private String state;
}
