package com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO;

import com.digitInsurance.bookStoreServicesApp.model.PaymentDetails;
import com.digitInsurance.bookStoreServicesApp.model.ShippingDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    //private ShippingDetails shippingDetails;
    private PaymentDetails paymentDetails;
}

