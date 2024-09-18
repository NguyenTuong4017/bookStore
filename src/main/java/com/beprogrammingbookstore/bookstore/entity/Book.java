package com.beprogrammingbookstore.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float price;
    private String title, author, publicYear, isbn;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Book() {

    }

    public Book(String title, String author, String publicYear, String isbn, float price) {
        this.title = title;
        this.author = author;
        this.publicYear = publicYear;
        this.isbn = isbn;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicYear() {
        return publicYear;
    }

    public void setPublicYear(String publicYear) {
        this.publicYear = publicYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        if (this.category != null)
            return "Book [id=" + id + ", price=" + price + ", title=" + title + ", author=" + author + ", publicYear="
                    + publicYear + ", isbn=" + isbn + ", category=" + this.getCategory() + "]";
        else
            return "Book [id=" + id + ", price=" + price + ", title=" + title + ", author=" + author + ", year="
                    + publicYear
                    + ", isbn=" + isbn + "]";

    }

}
