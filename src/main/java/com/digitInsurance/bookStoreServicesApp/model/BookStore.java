package com.digitInsurance.bookStoreServicesApp.model;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookStoreDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_store")
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String price;
    private Long stock;

    public BookStore(BookStoreDTO bookStoreDTO) {
        this.name = bookStoreDTO.getName();
        this.description = bookStoreDTO.getDescription();
        this.price = bookStoreDTO.getPrice();
        this.stock = bookStoreDTO.getStock();
    }
}
