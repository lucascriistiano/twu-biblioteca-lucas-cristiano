package com.twu.biblioteca.ui;

import com.twu.biblioteca.actions.CheckoutItem;
import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.actions.QuitApplicationAction;
import com.twu.biblioteca.actions.ReturnItem;
import com.twu.biblioteca.actions.books.ListBooks;
import com.twu.biblioteca.actions.movies.ListMovies;
import com.twu.biblioteca.service.BooksService;
import com.twu.biblioteca.service.MoviesService;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

import java.util.HashMap;
import java.util.Map;

public class MenuNavigator {

    private OutputBuilder outputBuilder;
    private Map<Integer, MenuAction> options;

    public MenuNavigator(OutputBuilder outputBuilder, BibliotecaCLI cli) {
        this.outputBuilder = outputBuilder;

        this.options = new HashMap<>();
        this.options.put(1, new ListBooks(outputBuilder, BooksService.getInstance(), "Book"));
        this.options.put(2, new CheckoutItem<>(outputBuilder, cli, BooksService.getInstance(), "Book"));
        this.options.put(3, new ReturnItem<>(outputBuilder, cli, BooksService.getInstance(), "Book"));
        this.options.put(4, new ListMovies(outputBuilder, MoviesService.getInstance(), "Movie"));
        this.options.put(5, new CheckoutItem<>(outputBuilder, cli, MoviesService.getInstance(), "Movie"));
        this.options.put(6, new ReturnItem<>(outputBuilder, cli, MoviesService.getInstance(), "Movie"));
        this.options.put(7, new QuitApplicationAction());
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
