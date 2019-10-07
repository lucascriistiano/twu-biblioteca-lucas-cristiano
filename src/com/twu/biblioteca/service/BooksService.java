package com.twu.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

public class BooksService {

    private List<String> books;

    private static BooksService instance;

    public static BooksService getInstance() {
        if (instance == null)
            instance = new BooksService();
        return instance;
    }

    private BooksService() {
        this.books = new ArrayList<>();
        this.books.add("Milkman: A Novel");
        this.books.add("City of Girls: A Novel");
        this.books.add("The Silent Patient");
        this.books.add("Once More We Saw Stars: A Memoir");
    }

    public List<String> listBooks() {
        return books;
    }

}
