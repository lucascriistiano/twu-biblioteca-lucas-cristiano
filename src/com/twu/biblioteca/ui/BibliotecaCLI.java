package com.twu.biblioteca.ui;

import java.util.Scanner;

public class BibliotecaCLI {

    private static Scanner sc = new Scanner(System.in);

    public BibliotecaCLI() { }

    public String readInput() {
        System.out.print("Type your input: ");
        String input = sc.nextLine();
        return input;
    }

    public String showOutput(String output) {
        System.out.println(output);
        return output;
    }

    public void printBlankLine() {
        System.out.println();
    }

}
