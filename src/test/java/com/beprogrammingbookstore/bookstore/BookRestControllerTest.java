package com.beprogrammingbookstore.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.beprogrammingbookstore.bookstore.controller.BookController;
import com.beprogrammingbookstore.bookstore.entity.Book;
import com.beprogrammingbookstore.bookstore.entity.BookRepository;
import com.beprogrammingbookstore.bookstore.entity.Category;
import com.beprogrammingbookstore.bookstore.entity.CategoryRepository;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@WebMvcTest(BookController.class)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        Category fiction = categoryRepository.save(new Category("Fiction"));
        Book book = new Book("Title", "Sample Author", "1999", "902835095", 19.99f, fiction);
        List<Book> bookList = Arrays.asList(book);
        given(bookRepository.findAll()).willReturn(bookList);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
