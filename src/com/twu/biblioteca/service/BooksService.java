package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.BookStatus;
import com.twu.biblioteca.service.exception.NonExistentBookException;
import com.twu.biblioteca.service.exception.UnavailableBookException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BooksService {

    private List<Book> books;

    private static BooksService instance;

    public static BooksService getInstance() {
        if (instance == null)
            instance = new BooksService();
        return instance;
    }

    private BooksService() {
        this.books = new ArrayList<>();
        this.books.add(new Book("Milkman: A Novel", "Anna Burns", 2018));
        this.books.add(new Book("City of Girls: A Novel", "Elizabeth Gilbert", 2019));
        this.books.add(new Book("The Silent Patient", "Alex Michaelides", 2019));
        this.books.add(new Book("Once More We Saw Stars: A Memoir", "Jayson Greene", 2019));
    }

    public List<Book> listBooks() {
        return books;
    }

    public List<Book> listAvailableBooks() {
        return books.stream().filter(b -> b.getStatus() == BookStatus.AVAILABLE).collect(Collectors.toList());
    }

    public void checkoutBook(Integer bookID) throws UnavailableBookException {
        Book book = find(bookID);
        if (book.getStatus() == BookStatus.NOT_AVAILABLE) {
            throw new UnavailableBookException();
        }
        book.setStatus(BookStatus.NOT_AVAILABLE);
    }

    public Book find(Integer bookID) throws NonExistentBookException {
        Book book = books.stream()
                .filter(b -> b.getId().equals(bookID))
                .findAny()
                .orElse(null);
        if (book == null) {
            throw new NonExistentBookException();
        }
        return book;
    }
}
