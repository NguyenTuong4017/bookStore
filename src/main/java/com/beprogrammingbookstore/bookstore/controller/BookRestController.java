package com.beprogrammingbookstore.bookstore.controller;

import org.springframework.web.bind.annotation.RestController;
import com.beprogrammingbookstore.bookstore.entity.Book;
import com.beprogrammingbookstore.bookstore.entity.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BookRestController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("booklist/export")
    public List<Book> bookList() {
        List<Book> bookList = new ArrayList<>();
        bookList = bookRepository.findAll();

        return bookList;
    }

    @RequestMapping("booklist/findById")
    public Book book(@RequestParam(value = "id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.get();
    }

}
