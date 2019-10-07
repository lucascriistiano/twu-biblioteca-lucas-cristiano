package com.twu.biblioteca;

import com.twu.biblioteca.ui.BibliotecaCLI;

public class BibliotecaApp {

    private final BibliotecaCLI bibliotecaCLI;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.start();
    }

    public BibliotecaApp() {
        bibliotecaCLI = new BibliotecaCLI();
    }

    public BibliotecaApp(BibliotecaCLI cli) {
        bibliotecaCLI = cli;
    }

    public void start() {
        bibliotecaCLI.showOutput(generateWelcomeMessage());
        bibliotecaCLI.printBlankLine();
        bibliotecaCLI.showOutput(generateMenu());
        String input = bibliotecaCLI.readInput();
        System.out.println(String.format("Selected %s", input));
    }

    public String generateWelcomeMessage() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public String generateMenu() {
        return "1 - List of books";
    }

}
