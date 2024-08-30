package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.feedbackDTO.FeedbackRequest;
import com.digitInsurance.bookStoreServicesApp.model.Feedback;

import java.util.List;

public interface FeedbackService {
    public Feedback submitFeedback(Long userId, FeedbackRequest feedbackRequestDTO);

    public List<Feedback> getFeedbackByBookId(Long productId);
}