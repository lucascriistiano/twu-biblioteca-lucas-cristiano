package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Book;

import java.util.Arrays;

public class BooksService extends ItemService<Book> {

    private static BooksService instance;

    public static BooksService getInstance() {
        if (instance == null)
            instance = new BooksService();
        return instance;
    }

    private BooksService() {
        super(Arrays.asList(
                new Book("Milkman: A Novel", "Anna Burns", 2018),
                new Book("City of Girls: A Novel", "Elizabeth Gilbert", 2019),
                new Book("The Silent Patient", "Alex Michaelides", 2019),
                new Book("Once More We Saw Stars: A Memoir", "Jayson Greene", 2019))
        );
    }

}
