package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.service.BooksService;
import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

public class CheckoutBook extends MenuAction {

    private BibliotecaCLI bibliotecaCLI;

    public CheckoutBook(BibliotecaCLI bibliotecaCLI) {
        super("Checkout book");
        this.bibliotecaCLI = bibliotecaCLI;
    }

    @Override
    public void run() {
        System.out.println("====== Book Checkout ======");

        bibliotecaCLI.showOutput("Type the ID of the book to checkout: ");
        try {
            Integer bookID = BibliotecaCLI.parseMenuInput(bibliotecaCLI.readInput());

            BooksService booksService = BooksService.getInstance();
            Book book = booksService.find(bookID);
            booksService.checkoutBook(book);
        } catch (InvalidOptionException e) {
           e.printStackTrace();
        }
    }

}
