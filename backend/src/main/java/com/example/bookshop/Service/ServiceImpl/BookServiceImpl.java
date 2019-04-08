package com.example.bookshop.Service.ServiceImpl;

import com.example.bookshop.DAO.BookRepository;
import com.example.bookshop.Model.Book;
import com.example.bookshop.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
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

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void changeNum(int coding, int num) {
        Book book = bookRepository.findBookByCoding(coding).get();
        book.setStorage(book.getStorage()+num);
    }


}
