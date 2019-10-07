package com.twu.biblioteca.ui;

import com.twu.biblioteca.ui.exception.InvalidOptionException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BibliotecaCLITest {

    @Test
    public void shouldParseMenuInputWhenValidIntegerString() {
        assertThat(BibliotecaCLI.parseMenuInput("1"), is(1));
    }

    @Test(expected = InvalidOptionException.class)
    public void shouldThrowExceptionWhenNonNumericValueProvided() {
        BibliotecaCLI.parseMenuInput("A");
    }

    @Test(expected = InvalidOptionException.class)
    public void shouldThrowExceptionWhenNonPositiveValueProvided() {
        BibliotecaCLI.parseMenuInput("-1");
    }

}
