package com.twu.biblioteca;


import com.twu.biblioteca.ui.BibliotecaCLI;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BibliotecaTest {

    BibliotecaApp app;
    BibliotecaCLI cli;

    @Before
    public void setUp() {
        cli = mock(BibliotecaCLI.class);
        app = new BibliotecaApp(cli);
    }

    @Test
    public void testWelcomeMessageIsShownOnApplicationStart() {
        app.start();
        String welcomeMessage = app.generateWelcomeMessage();
        verify(cli, times(1)).showOutput(welcomeMessage);
    }

    @Test
    public void testWelcomeMessageContent() {
        String message = app.generateWelcomeMessage();
        assertThat(message, is("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void testMainMenuIsGenerated() {
        app.start();
        String menu = app.generateMenu();
        verify(cli, times(1)).showOutput(menu);
    }

    @Test
    public void testMainMenuContent() {
        String message = app.generateMenu();
        assertThat(message, is("1 - List of books"));
    }

}
