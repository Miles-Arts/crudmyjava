package com.crudjava.mycrud.services;

import com.crudjava.mycrud.model.Book;
import com.crudjava.mycrud.repositories.BookRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRespository respository;

    public BookService(BookRespository respository) {
        this.respository = respository;
    }

    public List<Book> getAllBooks() {
        return respository.getAllBooks();
    }

}
