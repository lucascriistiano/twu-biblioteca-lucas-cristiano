package com.twu.biblioteca.actions.books;

import com.twu.biblioteca.actions.ListItems;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.service.ItemService;
import com.twu.biblioteca.ui.OutputBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListBooks extends ListItems<Book> {

    public ListBooks(OutputBuilder outputBuilder, ItemService<Book> service, String itemName) {
        super(outputBuilder, service, itemName);
    }

    @Override
    public void printFormattedItemsList(List<Book> items) {
        String format = generateBookPrintFormat(items);

        getOutputBuilder().addLine(String.format(format, "ID", "TITLE", "AUTHOR", "YEAR"));
        items.forEach(book -> getOutputBuilder().addLine(String.format(format, book.getId(), book.getTitle(),
                book.getAuthor(), book.getPublicationYear())));
    }

    private String generateBookPrintFormat(List<Book> books) {
        int longestBookTitle = Collections.max(books, Comparator.comparing(b -> b.getTitle().length())).getTitle().length();
        int longestAuthorName = Collections.max(books, Comparator.comparing(b -> b.getAuthor().length())).getAuthor().length();

        return String.format("%%-4s %%-%ds  %%-%ds  %%-4s", longestBookTitle, longestAuthorName);
    }

}
