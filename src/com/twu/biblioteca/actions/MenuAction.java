package com.twu.biblioteca.actions;

import com.twu.biblioteca.domain.Item;

public abstract class MenuAction<T extends Item> implements Action {

    private String description;

    public MenuAction() { }

    public MenuAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
