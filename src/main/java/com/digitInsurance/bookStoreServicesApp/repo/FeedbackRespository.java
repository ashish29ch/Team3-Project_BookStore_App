package com.digitInsurance.bookStoreServicesApp.repo;

import com.digitInsurance.bookStoreServicesApp.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRespository extends JpaRepository<Feedback,Long> {
    public List<Feedback> findByBookId(Long productId);

    public Optional<Feedback> findByUserIdAndBookId(Long id, Long bookId);
}
