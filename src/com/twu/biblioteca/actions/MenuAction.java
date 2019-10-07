package com.twu.biblioteca.actions;

public abstract class MenuAction implements Action {

    private String description;

    public MenuAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
