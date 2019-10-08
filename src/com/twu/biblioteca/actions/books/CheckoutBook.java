package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.service.BooksService;
import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.OutputBuilder;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

public class CheckoutBook extends MenuAction {

    private OutputBuilder outputBuilder;
    private BibliotecaCLI cli;

    public CheckoutBook(OutputBuilder outputBuilder, BibliotecaCLI cli) {
        super("Checkout book");
        this.outputBuilder = outputBuilder;
        this.cli = cli;
    }

    @Override
    public void run() {
        outputBuilder.addLine("====== Book Checkout ======");
        outputBuilder.add("Type the ID of the book to checkout: ");
        cli.showOutput(outputBuilder.buildAndClear());

        try {
            Integer bookID = BibliotecaCLI.parseMenuInput(cli.readInput());

            BooksService booksService = BooksService.getInstance();
            Book book = booksService.find(bookID);
            booksService.checkoutBook(book);

            outputBuilder.addLine("Thank you! Enjoy the book");
        } catch (InvalidOptionException e) {
            e.printStackTrace();
        }
    }

}