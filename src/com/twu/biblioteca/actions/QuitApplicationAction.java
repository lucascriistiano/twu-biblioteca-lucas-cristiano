package com.twu.biblioteca.actions;

public class QuitApplicationAction extends MenuAction {

    public QuitApplicationAction() {
        super("Quit");
    }

    @Override
    public boolean run() {
        return false;
    }

}
