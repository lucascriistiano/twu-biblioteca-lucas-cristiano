package com.twu.biblioteca.ui;


import com.twu.biblioteca.ui.exception.InvalidOptionException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MenuNavigatorTest {

    MenuNavigator navigator;

    @Before
    public void setUp() {
        navigator = new MenuNavigator();
    }

    @Test
    public void testMainMenuContent() {
        String menuStr = navigator.getMenu();
        assertThat(menuStr, is("1 - List of books"));
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
