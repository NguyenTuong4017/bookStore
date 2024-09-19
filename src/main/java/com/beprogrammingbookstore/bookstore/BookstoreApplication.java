package com.beprogrammingbookstore.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.beprogrammingbookstore.bookstore.entity.*;

@SpringBootApplication
public class BookstoreApplication {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Category fiction = categoryRepository.save(new Category("Fiction"));
			Category nonFiction = categoryRepository.save(new Category("Non-Fiction"));
			Category science = categoryRepository.save(new Category("Science"));
			Category history = categoryRepository.save(new Category("History"));
			Category biography = categoryRepository.save(new Category("Biography"));

			bookRepository.save(new Book("Hello World", "John Lennon", "1992", "2938429357892", 16f, biography));

		};
	}
}
