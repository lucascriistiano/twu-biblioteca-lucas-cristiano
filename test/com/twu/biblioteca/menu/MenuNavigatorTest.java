package com.twu.biblioteca.menu;


import com.twu.biblioteca.ui.MenuNavigator;
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

}
