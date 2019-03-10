package com.example.bookshop.Service;

import com.example.bookshop.Model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> queryAllBook();
    Optional<Book> queryOneBook(int coding);
}
