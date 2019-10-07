package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.MenuAction;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.service.BooksService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListBooks extends MenuAction {

    public ListBooks() {
        super("List of books");
    }

    @Override
    public void run() {
        System.out.println("====== Book List ======");

        BooksService booksService = BooksService.getInstance();
        List<Book> books = booksService.listBooks();

        printFormattedBookList(books);
        System.out.println();
    }

    private void printFormattedBookList(List<Book> books) {
        String format = generateBookPrintFormat(books);

        System.out.println(String.format(format, "TITLE", "AUTHOR", "YEAR"));
        books.forEach(book -> System.out.println(String.format(format, book.getTitle(), book.getAuthor(), book.getPublicationYear())));
    }

    private String generateBookPrintFormat(List<Book> books) {
        int longestBookTitle = Collections.max(books, Comparator.comparing(b -> b.getTitle().length())).getTitle().length();
        int longestAuthorName = Collections.max(books, Comparator.comparing(b -> b.getAuthor().length())).getAuthor().length();

        return String.format("%%-%ds  %%-%ds  %%-4s", longestBookTitle, longestAuthorName);
    }


}
