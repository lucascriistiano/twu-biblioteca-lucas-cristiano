package com.twu.biblioteca.ui;

import com.twu.biblioteca.actions.QuitApplicationAction;
import com.twu.biblioteca.actions.books.CheckoutBook;
import com.twu.biblioteca.actions.books.ListBooks;
import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.actions.books.ReturnBook;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

import java.util.HashMap;
import java.util.Map;

public class MenuNavigator {

    private OutputBuilder outputBuilder;
    private Map<Integer, MenuAction> options;

    public MenuNavigator(OutputBuilder outputBuilder, BibliotecaCLI cli) {
        this.outputBuilder = outputBuilder;

        this.options = new HashMap<>();
        this.options.put(1, new ListBooks(outputBuilder));
        this.options.put(2, new CheckoutBook(outputBuilder, cli));
        this.options.put(3, new ReturnBook(outputBuilder, cli));
        this.options.put(4, new QuitApplicationAction());
    }

    public boolean select(Integer input) throws InvalidOptionException {
        if (!this.isValidOption(input)) {
            throw new InvalidOptionException();
        }

        MenuAction action = options.get(input);
        return action.run();
    }

    public void buildMenu() {
        options.forEach((index, value) -> outputBuilder.addLine(formatMenuOption(index, value)));
    }

    private String formatMenuOption(Integer index, MenuAction action) {
        return String.format("%d - %s", index, action.getDescription());
    }

    private boolean isValidOption(Integer option) {
        return options.containsKey(option);
    }

}
