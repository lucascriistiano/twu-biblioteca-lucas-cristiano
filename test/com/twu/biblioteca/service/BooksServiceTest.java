package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.BookStatus;
import com.twu.biblioteca.service.exception.NonExistentBookException;
import com.twu.biblioteca.service.exception.NotCheckedOutBookException;
import com.twu.biblioteca.service.exception.UnavailableBookException;
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
        List<Book> books = service.listBooks();
        assertThat(books.size(),  is(greaterThan(0)));
    }

    @Test
    public void shouldReturnBookObject() {
        List<Book> books = service.listBooks();
        Book book = books.get(0);

        assertThat(book, hasProperty("title"));
        assertThat(book, hasProperty("author"));
        assertThat(book, hasProperty("publicationYear"));
        assertThat(book, hasProperty("status"));
    }

    @Test
    public void shouldReturnJustAvailableBooks() {
        List<Book> books = service.listAvailableBooks();
        assertThat(books, everyItem(hasProperty("status", is(BookStatus.AVAILABLE))));
    }

    @Test(expected = NonExistentBookException.class)
    public void shouldThrowExceptionOnNonExistingBookId() {
        assertThat(service.find(-1), is(nullValue()));
    }

    @Test
    public void shouldChangeBookStatusOnCheckout() {
        List<Book> books = service.listAvailableBooks();
        Book book = books.get(0);
        int bookId = book.getId();

        assertThat(book.getStatus(), is(BookStatus.AVAILABLE));

        service.checkoutBook(bookId);

        book = service.find(bookId);
        assertThat(book.getStatus(), is(BookStatus.NOT_AVAILABLE));
    }

    @Test
    public void shouldRemoveBookFromBooksListAfterCheckout() {
        List<Book> books = service.listAvailableBooks();
        int bookListSize = books.size();

        Book book = books.get(0);
        int bookId = book.getId();
        service.checkoutBook(bookId);

        books = service.listAvailableBooks();

        assertThat(books, everyItem(hasProperty("status", is(BookStatus.AVAILABLE))));
        assertThat(books.size(), is(bookListSize - 1));
        assertThat(books, everyItem(hasProperty("id", is(not(bookId)))));
    }

    @Test(expected = UnavailableBookException.class)
    public void shouldThrowExceptionOnCheckoutOfCheckedOutBook() {
        List<Book> books = service.listAvailableBooks();
        Book book = books.get(0);
        int bookId = book.getId();
        assertThat(book.getStatus(), is(BookStatus.AVAILABLE));

        service.checkoutBook(bookId);
        book = service.find(bookId);
        assertThat(book.getStatus(), is(BookStatus.NOT_AVAILABLE));

        service.checkoutBook(bookId);
    }

    @Test
    public void shouldChangeBookStatusOnReturn() {
        List<Book> books = service.listAvailableBooks();
        Book book = books.get(0);

        int bookId = book.getId();
        service.checkoutBook(bookId);

        service.returnBook(bookId);

        book = service.find(bookId);
        assertThat(book.getStatus(), is(BookStatus.AVAILABLE));
    }

    @Test
    public void shouldReturnBookToBooksListAfterReturned() {
        List<Book> books = service.listAvailableBooks();
        int bookListSize = books.size();

        Book book = books.get(0);
        int bookId = book.getId();
        service.checkoutBook(bookId);

        books = service.listAvailableBooks();
        assertThat(books, everyItem(hasProperty("id", is(not(bookId)))));
        assertThat(books.size(), is(bookListSize - 1));

        service.returnBook(bookId);

        books = service.listAvailableBooks();
        assertThat(books, everyItem(hasProperty("status", is(BookStatus.AVAILABLE))));
        assertThat(books.size(), is(bookListSize));
        assertThat(books, hasItem(hasProperty("id", is(bookId))));
    }

    @Test(expected = NotCheckedOutBookException.class)
    public void shouldThrowExceptionOnReturnOfNotCheckedOutBook() {
        List<Book> books = service.listAvailableBooks();
        Book book = books.get(0);
        int bookId = book.getId();
        assertThat(book.getStatus(), is(BookStatus.AVAILABLE));

        service.returnBook(bookId);
    }

}
