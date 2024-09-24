package com.beprogrammingbookstore.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.beprogrammingbookstore.bookstore.entity.Book;
import com.beprogrammingbookstore.bookstore.entity.BookRepository;
import com.beprogrammingbookstore.bookstore.entity.CategoryRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/booklist")
    public String showBooks(Model model) {
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("book", bookList);
        model.addAttribute("categories", categoryRepository.findAll());
        return "booklist";
    }

    @GetMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        bookRepository.deleteById(id);
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("book", bookList);
        return "redirect:/booklist";
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book newBook, Model model) {
        bookRepository.save(newBook);
        List<Book> bookList = bookRepository.findAll();

        model.addAttribute("book", bookList);
        return "redirect:/booklist";
    }

    @GetMapping("/editbook/{id}")
    public String editBook(Model model, @PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        model.addAttribute("book", book.get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }

    @PostMapping("/saveNewData/id={id}")
    public String saveOldBook(@ModelAttribute Book newBook, Model model, @PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        Book oldBook = book.get();

        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPrice(newBook.getPrice());
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setPublicYear(newBook.getPublicYear());
        oldBook.setTitle(newBook.getTitle());
        oldBook.setCategory(newBook.getCategory());

        bookRepository.save(oldBook);
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("book", bookList);
        return "redirect:/booklist";

    }

}
