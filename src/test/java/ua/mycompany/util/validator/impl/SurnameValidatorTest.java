package ua.mycompany.util.validator.impl;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SurnameValidatorTest {
    @Test
    public void shouldReturnTrueIfSurnameCorrect() {
        SurnameValidator surnameValidator = new SurnameValidator();
        assertThat(true, is(surnameValidator.validate("Tsaruk")));
        assertThat(false, is(surnameValidator.validate("sfds334")));
        assertThat(false, is(surnameValidator.validate("380qf45244")));
        assertThat(false, is(surnameValidator.validate("34223")));
        assertThat(false, is(surnameValidator.validate("@@@424...svd")));
    }

}