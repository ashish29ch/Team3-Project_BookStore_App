package com.digitInsurance.bookStoreServicesApp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookStore book;

    private Integer quantity = 0;
    private Double price;

    public CartItem(Cart cart, BookStore book, Integer quantity, Double price) {
        this.cart = cart;
        this.book = book;
        this.quantity = quantity != null ? quantity : 0;
        this.price = price;
    }
}
