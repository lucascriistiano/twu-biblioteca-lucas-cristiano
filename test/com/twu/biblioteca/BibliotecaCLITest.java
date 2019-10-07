package com.twu.biblioteca;

import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.exception.InvalidOptionException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BibliotecaCLITest {

    @Test
    public void testParseUserInputValidOption() {
        assertThat(BibliotecaCLI.parseMenuInput("1"), is(1));
    }

    @Test(expected = InvalidOptionException.class)
    public void testParseUserInputInvalidOption() {
        BibliotecaCLI.parseMenuInput("A");
    }

    @Test(expected = InvalidOptionException.class)
    public void testParseUserInputInvalidOptionNegativeNumber() {
        BibliotecaCLI.parseMenuInput("-1");
    }

}
