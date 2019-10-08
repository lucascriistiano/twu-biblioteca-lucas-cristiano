package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.service.BooksService;
import com.twu.biblioteca.service.exception.NonExistentBookException;
import com.twu.biblioteca.service.exception.NotCheckedOutBookException;
import com.twu.biblioteca.service.exception.UnavailableBookException;
import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.OutputBuilder;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

public class ReturnBook extends MenuAction {

    private OutputBuilder outputBuilder;
    private BibliotecaCLI cli;

    public ReturnBook(OutputBuilder outputBuilder, BibliotecaCLI cli) {
        super("Return a book");
        this.outputBuilder = outputBuilder;
        this.cli = cli;
    }

    @Override
    public void run() {
        outputBuilder.addLine("====== Book Return ======");
        outputBuilder.add("Type the ID of the book to return: ");
        cli.showOutput(outputBuilder.buildAndClear());

        try {
            Integer bookID = BibliotecaCLI.parseMenuInput(cli.readInput());

            BooksService booksService = BooksService.getInstance();
            booksService.returnBook(bookID);

            outputBuilder.addLine("Thank you for returning the book");
        } catch (InvalidOptionException | NonExistentBookException | NotCheckedOutBookException e) {
            outputBuilder.addLine("That is not a valid book to return");
        }
    }

}
