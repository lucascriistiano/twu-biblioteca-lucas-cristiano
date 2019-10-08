package com.twu.biblioteca.ui;

import com.twu.biblioteca.actions.books.CheckoutBook;
import com.twu.biblioteca.actions.books.ListBooks;
import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

import java.util.HashMap;
import java.util.Map;

public class MenuNavigator {

    private BibliotecaCLI bibliotecaCLI;
    private Map<Integer, MenuAction> options;

    public MenuNavigator(BibliotecaCLI bibliotecaCLI) {
        this.bibliotecaCLI = bibliotecaCLI;

        this.options = new HashMap<>();
        this.options.put(1, new ListBooks(bibliotecaCLI));
        this.options.put(2, new CheckoutBook(bibliotecaCLI));
    }

    public void select(Integer input) throws InvalidOptionException {
        if (!this.isValidOption(input)) {
            throw new InvalidOptionException();
        }

        MenuAction action = options.get(input);
        action.run();
    }

    private String getMenu() {
        StringBuilder menuStr = new StringBuilder();
        options.forEach((index, value) -> menuStr.append(formatMenuOption(index, value)));
        return menuStr.toString();
    }

    public void showMenu() {
        this.bibliotecaCLI.showOutputAndLineBreak(getMenu());
    }

    private String formatMenuOption(Integer index, MenuAction action) {
        return String.format("%d - %s\n", index, action.getDescription());
    }

    private boolean isValidOption(Integer option) {
        return options.containsKey(option);
    }

}
