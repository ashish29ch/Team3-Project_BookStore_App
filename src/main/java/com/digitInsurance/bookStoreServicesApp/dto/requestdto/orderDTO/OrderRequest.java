package com.digitInsurance.bookStoreServicesApp.dto.requestdto.orderDTO;

import com.digitInsurance.bookStoreServicesApp.model.PaymentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private PaymentDetails paymentDetails;
}

