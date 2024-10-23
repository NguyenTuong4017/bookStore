package com.beprogrammingbookstore.bookstore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.beprogrammingbookstore.bookstore.controller.BookController;
import com.beprogrammingbookstore.bookstore.entity.BookRepository;
import com.beprogrammingbookstore.bookstore.entity.CategoryRepository;
import com.beprogrammingbookstore.bookstore.entity.UserRepository;
import com.jayway.jsonpath.JsonPath;

@WebMvcTest(BookController.class)

class BookstoreApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookRepository bookRepository;

	@MockBean
	private CategoryRepository categoryRepository;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void titleCheck() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("nguyen", content);
	}

}
