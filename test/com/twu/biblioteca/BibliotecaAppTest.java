package com.twu.biblioteca;


import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.MenuNavigator;
import com.twu.biblioteca.ui.OutputBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {

    private BibliotecaApp app;
    private BibliotecaCLI cli;
    private OutputBuilder outputBuilder;
    private MenuNavigator navigator;

    @Before
    public void setUp() {
        cli = mock(BibliotecaCLI.class);
        outputBuilder = mock(OutputBuilder.class);
        navigator = mock(MenuNavigator.class);
        app = new BibliotecaApp(cli, outputBuilder, navigator);
    }

    @Test
    public void shouldShowWelcomeMessageOnApplicationStart() {
        app.start();
        String welcomeMessage = app.generateWelcomeMessage();
        verify(outputBuilder, times(1)).addLine(welcomeMessage);
    }

    @Test
    public void shouldShowExpectedWelcomeMessageContent() {
        String message = app.generateWelcomeMessage();
        assertThat(message, is("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void shouldShowMainMenuOnApplicationStart() {
        app.start();
        verify(navigator, times(1)).buildMenu();
    }

}
