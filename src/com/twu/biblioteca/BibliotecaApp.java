package com.twu.biblioteca;

import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.MenuNavigator;

public class BibliotecaApp {

    private final BibliotecaCLI bibliotecaCLI;
    private MenuNavigator menuNavigator;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.start();
    }

    public BibliotecaApp() {
        bibliotecaCLI = new BibliotecaCLI();
        menuNavigator = new MenuNavigator();
    }

    public BibliotecaApp(BibliotecaCLI cli, MenuNavigator navigator) {
        this.bibliotecaCLI = cli;
        this.menuNavigator = navigator;
    }

    public void start() {
        bibliotecaCLI.showOutput(generateWelcomeMessage());
        bibliotecaCLI.printBlankLine();
        showMenu();
    }

    public String generateWelcomeMessage() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public void showMenu() {
        bibliotecaCLI.showOutput(menuNavigator.getMenu());
        Integer input = bibliotecaCLI.readInput();
        menuNavigator.select(input);
    }

}
