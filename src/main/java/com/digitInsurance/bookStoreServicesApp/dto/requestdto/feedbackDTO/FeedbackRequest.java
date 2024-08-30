package com.digitInsurance.bookStoreServicesApp.dto.requestdto.feedbackDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FeedbackRequest {

    @NotBlank
    @NotBlank
    private Long bookId;

    @Min(1)
    @Max(5)
    private int rating;

    @NotEmpty
    @NotBlank
    private String message;
}