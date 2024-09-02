package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.feedbackDTO.FeedbackRequest;
import com.digitInsurance.bookStoreServicesApp.dto.responsedto.FeedbackResponseDTO;
import com.digitInsurance.bookStoreServicesApp.model.*;
import com.digitInsurance.bookStoreServicesApp.repo.BookStoreRepository;
import com.digitInsurance.bookStoreServicesApp.repo.FeedbackRespository;
import com.digitInsurance.bookStoreServicesApp.repo.OrderRepository;
import com.digitInsurance.bookStoreServicesApp.repo.UserRepository;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRespository feedbackRespository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Override
    public ResponseEntity<?> submitFeedback(Long userId, FeedbackRequest feedbackRequest) {

        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has purchased the product
        List<Order> orders = orderRepository.findByUserId(user.getId());

        boolean hasPurchased = false;
        for (Order order : orders) {
            for (OrderItem orderItem : order.getItems()) {
                if (orderItem.getBook().getId().equals(feedbackRequest.getBookId())) {
                    hasPurchased = true;
                    break;
                }
            }
            if (hasPurchased) {
                break;
            }
        }


        if (!hasPurchased) {
            throw new RuntimeException("User has not purchased this product");
        }

        // Check if feedback already exists for this order and product
        Optional<Feedback> existingFeedback = feedbackRespository.findByUserIdAndBookId(user.getId(), feedbackRequest.getBookId());
        if (existingFeedback.isPresent()) {
            throw new RuntimeException("Feedback already submitted for this product");
        }

        // Fetch the BookStore object using the BookRepository
        BookStore bookStore = bookStoreRepository.findById(feedbackRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Feedback feedback = new Feedback(user,
                bookStore,
                feedbackRequest.getRating(),
                feedbackRequest.getMessage()
        );
        feedbackRespository.save(feedback);
        FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
        feedbackResponseDTO.setId(feedback.getId());
        feedbackResponseDTO.setBookId(feedback.getBook().getId());
        feedbackResponseDTO.setUserId(feedback.getUser().getId());
        feedbackResponseDTO.setRating(feedback.getRating());
        feedbackResponseDTO.setMessage(feedback.getMessage());

        return ResponseEntity.status(201).body(feedbackResponseDTO);
    }

    @Override
    public List<Feedback> getFeedbackByBookId(Long productId) {
        return feedbackRespository.findByBookId(productId);
    }
}
