package com.twu.biblioteca.ui;

import com.twu.biblioteca.actions.books.ListBooks;
import com.twu.biblioteca.actions.MenuAction;

import java.util.HashMap;
import java.util.Map;

public class MenuNavigator {

    Map<Integer, MenuAction> options;

    public MenuNavigator() {
        this.options = new HashMap<>();
        this.options.put(1, new ListBooks());
    }

    public void select(Integer input) {
        MenuAction action = options.get(input);
        action.run();
    }

    public String getMenu() {
        StringBuilder menuStr = new StringBuilder();
        options.forEach((index, value) -> menuStr.append(formatMenuOption(index, value)));
        return menuStr.toString();
    }

    private String formatMenuOption(Integer index, MenuAction action) {
        return String.format("%d - %s", index, action.getDescription());
    }

}
