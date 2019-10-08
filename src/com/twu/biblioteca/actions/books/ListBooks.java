package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.service.BooksService;
import com.twu.biblioteca.ui.OutputBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListBooks extends MenuAction {

    private OutputBuilder outputBuilder;

    public ListBooks(OutputBuilder outputBuilder) {
        super("List of books");
        this.outputBuilder = outputBuilder;
    }

    @Override
    public boolean run() {
        System.out.println("====== Book List ======");

        BooksService booksService = BooksService.getInstance();
        List<Book> books = booksService.listAvailableBooks();

        printFormattedBookList(books);
        outputBuilder.addBlankLine();
        return true;
    }

    private void printFormattedBookList(List<Book> books) {
        String format = generateBookPrintFormat(books);

        outputBuilder.addLine(String.format(format, "ID", "TITLE", "AUTHOR", "YEAR"));
        books.forEach(book -> outputBuilder.addLine(String.format(format, book.getId(), book.getTitle(),
                book.getAuthor(), book.getPublicationYear())));
    }

    private String generateBookPrintFormat(List<Book> books) {
        int longestBookTitle = Collections.max(books, Comparator.comparing(b -> b.getTitle().length())).getTitle().length();
        int longestAuthorName = Collections.max(books, Comparator.comparing(b -> b.getAuthor().length())).getAuthor().length();

        return String.format("%%-4s %%-%ds  %%-%ds  %%-4s", longestBookTitle, longestAuthorName);
    }

}
