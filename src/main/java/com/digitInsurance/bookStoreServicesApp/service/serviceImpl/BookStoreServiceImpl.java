package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.BookStoreDTO;
import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import com.digitInsurance.bookStoreServicesApp.repo.BookStoreRepository;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookStoreServiceImpl implements BookStoreService {


    @Autowired
    BookStoreRepository bookStoreRepository;


    @Override
    public ResponseEntity<BookStore> addBook(BookStoreDTO bookStoreDTO) {
         BookStore bookStore = new BookStore(bookStoreDTO);
         return ResponseEntity.status(HttpStatus.OK).body(bookStoreRepository.save(bookStore));
    }
}
