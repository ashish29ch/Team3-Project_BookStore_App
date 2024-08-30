package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.feedbackDTO.FeedbackRequest;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.Feedback;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.FeedbackService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> submitFeedback(@RequestHeader("Authorization") String token, @RequestBody FeedbackRequest feedbackRequest) throws TokenNotValidException, TokenNotValidException {
        Long userId = JWTToken.getUserIdFromToken(token);
        Feedback feedback = feedbackService.submitFeedback(userId, feedbackRequest);
        return ResponseEntity.status(201).body(feedback);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Feedback>> getFeedbackByProductId(@PathVariable Long productId) {
        List<Feedback> feedbackList = feedbackService.getFeedbackByBookId(productId);
        return ResponseEntity.ok(feedbackList);
    }
}
