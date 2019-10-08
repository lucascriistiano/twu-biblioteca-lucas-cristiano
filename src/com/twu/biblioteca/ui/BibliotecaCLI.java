package com.twu.biblioteca.ui;

import com.twu.biblioteca.ui.exception.InvalidOptionException;

import java.util.Scanner;

public class BibliotecaCLI {

    private static Scanner sc = new Scanner(System.in);

    public BibliotecaCLI() { }

    public String readInput() {
        return sc.nextLine();
    }

    public static Integer parseMenuInput(String input) throws InvalidOptionException {
        try {
            int option = Integer.parseInt(input);
            if (option < 1) {
                throw new InvalidOptionException();
            }
            return option;
        } catch(NumberFormatException e) {
            throw new InvalidOptionException();
        }
    }

    public void showOutputAndLineBreak(String output) {
        System.out.println(output);
    }

    public void showOutput(String output) {
        System.out.print(output);
    }

    public void clearOutput() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
