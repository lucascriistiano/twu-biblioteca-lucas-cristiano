package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.MenuAction;

public class ListBooks extends MenuAction {

    public ListBooks() {
        super("List of books");
    }

    @Override
    public void run() {
        System.out.println("Will print list of books");
    }

}
