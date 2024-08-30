package com.digitInsurance.bookStoreServicesApp.dto.responsedto;

import com.digitInsurance.bookStoreServicesApp.model.BookStore;
import lombok.Data;

import java.util.List;

@Data
public class WishlistResponseDTO {
    private Long id;
    private List<BookStore> books;
}
