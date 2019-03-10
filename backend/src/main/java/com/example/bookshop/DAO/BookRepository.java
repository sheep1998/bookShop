package com.example.bookshop.DAO;

import com.example.bookshop.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer>{
    Optional<Book> findBookByCoding(int coding);
}
