package com.twu.biblioteca;

import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.MenuNavigator;
import com.twu.biblioteca.ui.OutputBuilder;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

public class BibliotecaApp {

    private final BibliotecaCLI bibliotecaCLI;
    private OutputBuilder outputBuilder = null;
    private MenuNavigator menuNavigator;
    private boolean running;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.start();
    }

    public BibliotecaApp() {
        this.bibliotecaCLI = new BibliotecaCLI();
        this.outputBuilder = new OutputBuilder();
        this.menuNavigator = new MenuNavigator(outputBuilder, bibliotecaCLI);
        this.running = false;
    }

    public BibliotecaApp(BibliotecaCLI cli, OutputBuilder outputBuilder, MenuNavigator navigator) {
        this.bibliotecaCLI = cli;
        this.outputBuilder = outputBuilder;
        this.menuNavigator = navigator;
        this.running = false;
    }

    public void start() {
        this.running = true;

        this.outputBuilder.clear();
        this.outputBuilder.addLine(generateWelcomeMessage());
        this.outputBuilder.addBlankLine();

        while(running) {
            this.menuNavigator.buildMenu();

            try {
                this.outputBuilder.add("Select an menu option: ");
                this.bibliotecaCLI.showOutput(this.outputBuilder.buildAndClear());

                Integer input = BibliotecaCLI.parseMenuInput(this.bibliotecaCLI.readInput());
                this.menuNavigator.select(input);
            } catch(InvalidOptionException e) {
                this.outputBuilder.addLine("Please select a valid option!");
            }
            this.outputBuilder.addBlankLine();
            this.bibliotecaCLI.showOutput(this.outputBuilder.buildAndClear());
        }
    }

    public String generateWelcomeMessage() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

}
