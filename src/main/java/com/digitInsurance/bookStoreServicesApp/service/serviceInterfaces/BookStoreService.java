package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.BookStoreDTO;
import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import org.springframework.http.ResponseEntity;

public interface BookStoreService {
    public ResponseEntity<BookStore> addBook(BookStoreDTO bookStoreDTO);
}
