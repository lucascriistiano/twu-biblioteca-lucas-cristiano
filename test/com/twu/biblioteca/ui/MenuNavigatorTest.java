package com.twu.biblioteca.ui;

import com.twu.biblioteca.ui.exception.InvalidOptionException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MenuNavigatorTest {

    private MenuNavigator navigator;
    private OutputBuilder outputBuilder;
    private BibliotecaCLI cli;

    @Before
    public void setUp() {
        cli = mock(BibliotecaCLI.class);
        outputBuilder = mock(OutputBuilder.class);
        navigator = new MenuNavigator(outputBuilder, cli);
    }

    @Test
    public void shouldReturnMainMenuContentString() {
        navigator.buildMenu();
        verify(outputBuilder, times(1)).addLine("1 - List of books");
    }

    @Test(expected = InvalidOptionException.class)
    public void shouldThrowExceptionOnSelectInvalidNegativeOption() {
        navigator.select(-1);
    }

    @Test(expected = InvalidOptionException.class)
    public void shouldThrowExceptionOnSelectInvalidNonPositiveOption() {
        navigator.select(0);
    }

    @Test(expected = InvalidOptionException.class)
    public void shouldThrowExceptionOnSelectInvalidNonexistentOption() {
        navigator.select(10);
    }

}
