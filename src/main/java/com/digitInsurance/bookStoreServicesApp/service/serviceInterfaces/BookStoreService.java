package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookStoreDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookUpdateDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.DeleteBookDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.BookAlreadyExists;
import com.digitInsurance.bookStoreServicesApp.exception.customException.BookNotFound;
import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {
    public ResponseEntity<BookStore> addBook(BookStoreDTO bookStoreDTO) throws BookAlreadyExists;

   public ResponseEntity<BookStore> updateBook(BookUpdateDTO bookUpdateDTO) throws BookNotFound;

   public ResponseEntity<?> deleteBook(DeleteBookDTO deleteBookDTO) throws BookNotFound;

    public Optional<List<BookStore>> getAllBooksList();

    Optional<BookStore> getBookById(Long id);
}
