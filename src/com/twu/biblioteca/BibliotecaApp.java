package com.twu.biblioteca;

import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.MenuNavigator;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

public class BibliotecaApp {

    private final BibliotecaCLI bibliotecaCLI;
    private MenuNavigator menuNavigator;
    private boolean running;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.start();
    }

    public BibliotecaApp() {
        this.bibliotecaCLI = new BibliotecaCLI();
        this.menuNavigator = new MenuNavigator();
        this.running = false;
    }

    public BibliotecaApp(BibliotecaCLI cli, MenuNavigator navigator) {
        this.bibliotecaCLI = cli;
        this.menuNavigator = navigator;
        this.running = false;
    }

    public void start() {
        this.running = true;

        this.bibliotecaCLI.showOutput(generateWelcomeMessage());
        this.bibliotecaCLI.printBlankLine();

        while(running) {
            this.showMenu();
            try {
                Integer input = this.readOption();
                this.menuNavigator.select(input);
            } catch(InvalidOptionException e) {
                this.bibliotecaCLI.showOutput("Please select a valid option!");
            }
            this.bibliotecaCLI.printBlankLine();
            this.bibliotecaCLI.clearOutput();
        }
    }

    public String generateWelcomeMessage() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public void showMenu() {
        this.bibliotecaCLI.showOutput(this.menuNavigator.getMenu());
    }

    private Integer readOption() throws InvalidOptionException {
        System.out.print("Select an menu option: ");
        String input = this.bibliotecaCLI.readInput();
        return BibliotecaCLI.parseMenuInput(input);
    }

}
