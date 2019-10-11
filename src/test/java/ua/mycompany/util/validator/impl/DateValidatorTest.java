package ua.mycompany.util.validator.impl;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DateValidatorTest {
    @Test
    public void shouldReturnTrueIfDataIsCorrect() {
        DateValidator dataValidator = new DateValidator();
        assertThat(true, is(dataValidator.validate("07/09/2000")));
        assertThat(false, is(dataValidator.validate("11.11.1997")));
        assertThat(false, is(dataValidator.validate("77/15/2222")));
        assertThat(false, is(dataValidator.validate("fvdsdsvv@fdvfd")));
    }

}