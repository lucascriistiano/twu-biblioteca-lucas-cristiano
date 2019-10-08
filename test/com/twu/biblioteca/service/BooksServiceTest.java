package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.ItemStatus;
import com.twu.biblioteca.service.exception.NonExistentItemException;
import com.twu.biblioteca.service.exception.NotCheckedOutItemException;
import com.twu.biblioteca.service.exception.UnavailableItemException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class BooksServiceTest {

    private BooksService service;

    @Before
    public void setUp() {
        service = BooksService.getInstance();
    }

    @Test
    public void shouldReturnNonEmptyListOfBooks() {
        List<Book> books = service.listItems();
        assertThat(books.size(),  is(greaterThan(0)));
    }

    @Test
    public void shouldReturnBookObject() {
        List<Book> books = service.listItems();
        Book book = books.get(0);

        assertThat(book, hasProperty("title"));
        assertThat(book, hasProperty("author"));
        assertThat(book, hasProperty("publicationYear"));
        assertThat(book, hasProperty("status"));
    }

    @Test
    public void shouldReturnJustAvailableBooks() {
        List<Book> books = service.listAvailableItems();
        assertThat(books, everyItem(hasProperty("status", is(ItemStatus.AVAILABLE))));
    }

    @Test(expected = NonExistentItemException.class)
    public void shouldThrowExceptionOnNonExistingBookId() {
        assertThat(service.findItem(-1), is(nullValue()));
    }

    @Test
    public void shouldChangeBookStatusOnCheckout() {
        List<Book> books = service.listAvailableItems();
        Book book = books.get(0);
        int bookId = book.getId();

        assertThat(book.getStatus(), is(ItemStatus.AVAILABLE));

        service.checkoutItem(bookId);

        book = service.findItem(bookId);
        assertThat(book.getStatus(), is(ItemStatus.NOT_AVAILABLE));
    }

    @Test
    public void shouldRemoveBookFromBooksListAfterCheckout() {
        List<Book> books = service.listAvailableItems();
        int bookListSize = books.size();

        Book book = books.get(0);
        int bookId = book.getId();
        service.checkoutItem(bookId);

        books = service.listAvailableItems();

        assertThat(books, everyItem(hasProperty("status", is(ItemStatus.AVAILABLE))));
        assertThat(books.size(), is(bookListSize - 1));
        assertThat(books, everyItem(hasProperty("id", is(not(bookId)))));
    }

    @Test(expected = UnavailableItemException.class)
    public void shouldThrowExceptionOnCheckoutOfCheckedOutBook() {
        List<Book> books = service.listAvailableItems();
        Book book = books.get(0);
        int bookId = book.getId();
        assertThat(book.getStatus(), is(ItemStatus.AVAILABLE));

        service.checkoutItem(bookId);
        book = service.findItem(bookId);
        assertThat(book.getStatus(), is(ItemStatus.NOT_AVAILABLE));

        service.checkoutItem(bookId);
    }

    @Test
    public void shouldChangeBookStatusOnReturn() {
        List<Book> books = service.listAvailableItems();
        Book book = books.get(0);

        int bookId = book.getId();
        service.checkoutItem(bookId);

        service.returnItem(bookId);

        book = service.findItem(bookId);
        assertThat(book.getStatus(), is(ItemStatus.AVAILABLE));
    }

    @Test
    public void shouldReturnBookToBooksListAfterReturned() {
        List<Book> books = service.listAvailableItems();
        int bookListSize = books.size();

        Book book = books.get(0);
        int bookId = book.getId();
        service.checkoutItem(bookId);

        books = service.listAvailableItems();
        assertThat(books, everyItem(hasProperty("id", is(not(bookId)))));
        assertThat(books.size(), is(bookListSize - 1));

        service.returnItem(bookId);

        books = service.listAvailableItems();
        assertThat(books, everyItem(hasProperty("status", is(ItemStatus.AVAILABLE))));
        assertThat(books.size(), is(bookListSize));
        assertThat(books, hasItem(hasProperty("id", is(bookId))));
    }

    @Test(expected = NotCheckedOutItemException.class)
    public void shouldThrowExceptionOnReturnOfNotCheckedOutBook() {
        List<Book> books = service.listAvailableItems();
        Book book = books.get(0);
        int bookId = book.getId();
        assertThat(book.getStatus(), is(ItemStatus.AVAILABLE));

        service.returnItem(bookId);
    }

}
