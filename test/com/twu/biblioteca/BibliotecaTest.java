package com.twu.biblioteca;


import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.MenuNavigator;
import com.twu.biblioteca.ui.exception.InvalidOptionException;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BibliotecaTest {

    BibliotecaApp app;
    BibliotecaCLI cli;
    MenuNavigator navigator;

    @Before
    public void setUp() {
        cli = mock(BibliotecaCLI.class);
        navigator = mock(MenuNavigator.class);
        app = new BibliotecaApp(cli, navigator);
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
    public void testMainMenuIsShown() {
        app.start();
        verify(navigator, times(1)).getMenu();
    }

}
