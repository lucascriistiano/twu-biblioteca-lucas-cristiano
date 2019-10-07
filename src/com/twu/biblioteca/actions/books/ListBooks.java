package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.service.BooksService;

import java.util.List;

public class ListBooks extends MenuAction {

    public ListBooks() {
        super("List of books");
    }

    @Override
    public void run() {
        System.out.println("====== Book List ======");

        BooksService booksService = BooksService.getInstance();
        List<String> books = booksService.listBooks();
        books.forEach(System.out::println);

        System.out.println();
    }

}
