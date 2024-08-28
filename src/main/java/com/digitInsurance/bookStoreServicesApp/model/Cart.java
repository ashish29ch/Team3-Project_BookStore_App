package com.digitInsurance.bookStoreServicesApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
