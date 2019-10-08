package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.service.BooksService;
import com.twu.biblioteca.ui.BibliotecaCLI;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListBooks extends MenuAction {

    private BibliotecaCLI bibliotecaCLI;

    public ListBooks(BibliotecaCLI bibliotecaCLI) {
        super("List of books");
        this.bibliotecaCLI = bibliotecaCLI;
    }

    @Override
    public void run() {
        System.out.println("====== Book List ======");

        BooksService booksService = BooksService.getInstance();
        List<Book> books = booksService.listAvailableBooks();

        printFormattedBookList(books);
        bibliotecaCLI.printBlankLine();
    }

    private void printFormattedBookList(List<Book> books) {
        String format = generateBookPrintFormat(books);

        bibliotecaCLI.showOutputAndLineBreak(String.format(format, "ID", "TITLE", "AUTHOR", "YEAR"));
        books.forEach(book -> bibliotecaCLI.showOutputAndLineBreak(String.format(format, book.getId(), book.getTitle(),
                book.getAuthor(), book.getPublicationYear())));
    }

    private String generateBookPrintFormat(List<Book> books) {
        int longestBookTitle = Collections.max(books, Comparator.comparing(b -> b.getTitle().length())).getTitle().length();
        int longestAuthorName = Collections.max(books, Comparator.comparing(b -> b.getAuthor().length())).getAuthor().length();

        return String.format("%%-4s %%-%ds  %%-%ds  %%-4s", longestBookTitle, longestAuthorName);
    }

}
