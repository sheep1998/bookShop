package com.example.bookshop.Service.ServiceImpl;

import com.example.bookshop.DAO.BookRepository;
import com.example.bookshop.Model.Book;
import com.example.bookshop.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> queryAllBook() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Optional<Book> queryOneBook(int coding) {
        Optional<Book> book = bookRepository.findBookByCoding(coding);
        return book;
    }
}
