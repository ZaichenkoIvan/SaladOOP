package ua.mycompany.util.validator.impl;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmailValidatorTest {
    @Test
    public void shouldReturnTrueIfEmailCorrect() {
        EmailValidator emailValidator = new EmailValidator();
        assertThat(true, is(emailValidator.validate("vova@gmail.com")));
        assertThat(false, is(emailValidator.validate("fvdsdvsdvsv@")));
        assertThat(false, is(emailValidator.validate("fvdsv@fd32rvfd.")));
        assertThat(false, is(emailValidator.validate("fvdsv133fdvfd@.gmail.com")));
        assertThat(false, is(emailValidator.validate("fvdsdsvv@fdvfd")));


    }
}