package com.digitInsurance.bookStoreServicesApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    private Double totalAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    private String status;

    @Embedded
    private ShippingDetails shippingDetails;

    @Embedded
    private PaymentDetails paymentDetails;

    private String confirmationNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmationDate;

    @PrePersist
    @PreUpdate
    public void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
