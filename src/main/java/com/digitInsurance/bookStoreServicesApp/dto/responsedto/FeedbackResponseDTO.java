package com.digitInsurance.bookStoreServicesApp.dto.responsedto;

import lombok.Data;

@Data
public class FeedbackResponseDTO {
    private Long id;
    private Long bookId;
    private Long userId;
    private int rating;
    private String message;
}