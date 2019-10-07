package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


public class BooksServiceTest {

    BooksService service;

    @Before
    public void setUp() {
        service = BooksService.getInstance();
    }

    @Test
    public void shouldReturnNonEmptyListOfBooks() {
        List<Book> books = service.listBooks();
        assertThat(books.size(),  is(greaterThan(0)));
    }

}
