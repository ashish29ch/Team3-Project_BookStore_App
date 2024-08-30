package com.digitInsurance.bookStoreServicesApp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToMany
//    @JoinTable(
//            name = "wishlist_books",
//            joinColumns = @JoinColumn(name = "wishlist_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id")
//    )
    private List<BookStore> books = new ArrayList<>();
}
