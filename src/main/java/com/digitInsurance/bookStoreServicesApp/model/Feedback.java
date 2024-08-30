package com.digitInsurance.bookStoreServicesApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookStore book;

    @Min(1)
    @Max(5)
    private int rating;

    @NotEmpty
    @NotBlank
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date feedbackDate;


    public Feedback(Users user, BookStore book, int rating, String message) {
        this.user = user;
        this.book = book;
        this.rating = rating;
        this.message = message;
        this.feedbackDate = new Date();
    }

}
