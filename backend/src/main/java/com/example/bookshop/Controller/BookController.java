package com.example.bookshop.Controller;

import com.example.bookshop.Model.Book;
import com.example.bookshop.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value="/book/all")
    @ResponseBody
    public List<Book> queryAllBook(){
        return bookService.queryAllBook();
    };

    @PostMapping(value = "/book/single")
    @ResponseBody
    public Optional<Book> single(@RequestParam("coding")int coding){
        return bookService.queryOneBook(coding);
    }
}
