package ua.mycompany.util.validator.impl;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneValidatorTest {
    @Test
    public void shouldReturnTrueIfPhoneCorrect() {
        PhoneValidator phoneValidator = new PhoneValidator();
        assertThat(true, is(phoneValidator.validate("380967711333")));
        assertThat(false, is(phoneValidator.validate("wfwef")));
        assertThat(false, is(phoneValidator.validate("380qf45244")));
        assertThat(false, is(phoneValidator.validate("V342234df")));
        assertThat(false, is(phoneValidator.validate("@@@424...svd")));
    }

}