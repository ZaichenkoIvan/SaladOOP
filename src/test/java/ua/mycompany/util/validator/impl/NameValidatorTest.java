package ua.mycompany.util.validator.impl;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NameValidatorTest {
    @Test
    public void shouldReturnTrueIfNameCorrect() {
        NameValidator nameValidator = new NameValidator();
        assertThat(true, is(nameValidator.validate("Vova")));
        assertThat(false, is(nameValidator.validate("V")));
        assertThat(false, is(nameValidator.validate("Vova123")));
        assertThat(false, is(nameValidator.validate("Vova@")));
        assertThat(false, is(nameValidator.validate("Vova@wefwe")));
    }

}