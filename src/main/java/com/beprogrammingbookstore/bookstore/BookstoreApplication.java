package com.beprogrammingbookstore.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.beprogrammingbookstore.bookstore.entity.*;

@SpringBootApplication
public class BookstoreApplication {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return args -> {
			Category fiction = categoryRepository.save(new Category("Fiction"));
			Category nonFiction = categoryRepository.save(new Category("Non-Fiction"));
			Category science = categoryRepository.save(new Category("Science"));
			Category history = categoryRepository.save(new Category("History"));
			Category biography = categoryRepository.save(new Category("Biography"));

			bookRepository
					.save(new Book("The Infinite Journey", "Alice Walker", "1985", "1234567890123", 12.99f, fiction));
			bookRepository.save(
					new Book("The Science of Everything", "Isaac Newton", "2001", "9876543210987", 18.99f, science));
			bookRepository
					.save(new Book("Untold Histories", "David Mitchell", "1999", "1122334455667", 14.50f, history));
			bookRepository
					.save(new Book("Life and Legacy", "Maya Angelou", "1978", "6677889900112", 16.75f, biography));
			bookRepository
					.save(new Book("The Modern World", "George Orwell", "1961", "9988776655443", 11.99f, nonFiction));
			bookRepository.save(
					new Book("A Journey Through Time", "Stephen Hawking", "1988", "1231231231231", 20.50f, science));
			bookRepository.save(new Book("Stories Untold", "Mark Twain", "1902", "7897897897897", 15.00f, fiction));
			bookRepository.save(
					new Book("The History of Civilization", "Mary Beard", "2010", "4564564564564", 21.99f, history));
			bookRepository.save(new Book("Becoming", "Michelle Obama", "2018", "6546546546546", 24.99f, biography));
			bookRepository.save(
					new Book("Breaking Boundaries", "Neil deGrasse Tyson", "2022", "3213213213210", 19.99f, science));

			UserEntity user1 = new UserEntity("user", encoder.encode("user"), "user@gmail.com", "USER");
			UserEntity user2 = new UserEntity("admin", encoder.encode("admin"), "admin@gmail.com", "ADMIN");

			userRepository.save(user1);
			userRepository.save(user2);

			System.out.println("User1's password: " + user1.getPassword());
		};
	}
}
