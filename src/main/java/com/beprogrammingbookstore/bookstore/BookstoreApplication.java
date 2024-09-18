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
			categoryRepository.save(new Category("Fiction"));
			categoryRepository.save(new Category("Non-Fiction"));
			categoryRepository.save(new Category("Science"));
			categoryRepository.save(new Category("History"));
			categoryRepository.save(new Category("Biography"));
		};
	}
}
