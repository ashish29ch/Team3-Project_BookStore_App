package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookStoreDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.BookUpdateDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.bookDTO.DeleteBookDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.BookAlreadyExists;
import com.digitInsurance.bookStoreServicesApp.exception.customException.BookNotFound;
import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import com.digitInsurance.bookStoreServicesApp.repo.BookStoreRepository;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.BookStoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {


    @Autowired
    BookStoreRepository bookStoreRepository;


    @Override
    public ResponseEntity<BookStore> addBook(BookStoreDTO bookStoreDTO) throws BookAlreadyExists {
         BookStore bookStore = new BookStore(bookStoreDTO);

        Optional<BookStore> bk = bookStoreRepository.findByName(bookStoreDTO.getName());
        if(bk.isPresent()){
            throw new BookAlreadyExists("This book name is already present, you can update it if you want");
        }
         return ResponseEntity.status(HttpStatus.OK).body(bookStoreRepository.save(bookStore));
    }

    @Override
    public ResponseEntity<BookStore> updateBook(BookUpdateDTO bookUpdateDTO) throws BookNotFound {

        Optional<BookStore> bk= bookStoreRepository.findByName(bookUpdateDTO.getName());

        if(bk.isPresent()){
            BookStore book= bk.get();
            if(Objects.nonNull(bookUpdateDTO.getName()) &&
                    !"".equalsIgnoreCase(bookUpdateDTO.getName())){
                book.setName(bookUpdateDTO.getName());
            }
            if(Objects.nonNull(bookUpdateDTO.getDescription()) &&
                    !"".equalsIgnoreCase(bookUpdateDTO.getDescription())){
                book.setDescription(bookUpdateDTO.getDescription());
            }
            if(Objects.nonNull(bookUpdateDTO.getPrice()) &&
                    !"".equalsIgnoreCase(bookUpdateDTO.getPrice())){
                book.setPrice(bookUpdateDTO.getPrice());
            }
            if(Objects.nonNull(bookUpdateDTO.getStock())){
                book.setStock(bookUpdateDTO.getStock());
            }
            bookStoreRepository.save(book);
            return ResponseEntity.ok(book);
        }
        else{
            throw new BookNotFound("book does not exist");
        }
    }

    @Override
    public ResponseEntity<?> deleteBook(@Valid DeleteBookDTO deleteBookDTO) throws BookNotFound {

        Optional<BookStore> bk = bookStoreRepository.findByName(deleteBookDTO.getName());

        if (bk.isPresent()) {
            bookStoreRepository.delete(bk.get());
            return ResponseEntity.status(HttpStatus.OK).body("Book Delete Successfully");
        } else {
            throw new BookNotFound("Book does not exist");
        }
    }

    @Override
    public Optional<List<BookStore>> getAllBooksList() {
        return Optional.of(bookStoreRepository.findAll());
    }

    @Override
    public Optional<BookStore> getBookById(Long id) {
        return bookStoreRepository.findById(id);
    }
}

