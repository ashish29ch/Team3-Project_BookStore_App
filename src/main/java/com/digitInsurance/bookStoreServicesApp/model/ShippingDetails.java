package com.digitInsurance.bookStoreServicesApp.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetails {
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
