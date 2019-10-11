package ua.mycompany.util.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.util.validator.impl.EmailValidator;
import ua.mycompany.util.validator.impl.NameValidator;
import ua.mycompany.util.validator.impl.PhoneValidator;
import ua.mycompany.util.validator.impl.SurnameValidator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserValidatorTest {

    @Mock
    private EmailValidator emailValidator;
    @Mock
    private NameValidator nameValidator;
    @Mock
    private PhoneValidator phoneValidator;
    @Mock
    private SurnameValidator surnameValidator;
    @InjectMocks
    private UserValidator userValidator;

    @Test
    public void shouldReturnTrueIfCustomerCorrect() {
        Customer customerWithoutError = Customer.builder()
                .withName("Vova")
                .withSurname("Tsaruk")
                .withEmail("vova@gmail.com")
                .withPhoneNumber("380779933311")
                .build();

        when(nameValidator.validate(customerWithoutError.getName())).thenReturn(true);
        when(surnameValidator.validate(customerWithoutError.getSurname())).thenReturn(true);
        when(phoneValidator.validate(customerWithoutError.getPhoneNumber())).thenReturn(true);
        when(emailValidator.validate(customerWithoutError.getEmail())).thenReturn(true);

        assertThat(true, is(userValidator.validate(customerWithoutError)));

    }

    @Test
    public void shouldReturnFalseIfNameOfCustomerNotCorrect() {
        Customer customerWithNameError = Customer.builder()
                .withName("3e23edf")
                .withSurname("Tsaruk")
                .withEmail("vova@gmail.com")
                .withPhoneNumber("380779933311")
                .build();

        when(nameValidator.validate(customerWithNameError.getName())).thenReturn(false);
        when(surnameValidator.validate(customerWithNameError.getSurname())).thenReturn(true);
        when(phoneValidator.validate(customerWithNameError.getPhoneNumber())).thenReturn(true);
        when(emailValidator.validate(customerWithNameError.getEmail())).thenReturn(true);

        assertThat(false, is(userValidator.validate(customerWithNameError)));

    }

    @Test
    public void shouldReturnFalseIfSurnameOfCustomerNotCorrect() {
        Customer customerWithSurnameError = Customer.builder()
                .withName("Vova")
                .withSurname("13442ds")
                .withEmail("vova@gmail.com")
                .withPhoneNumber("380779933311")
                .build();

        when(nameValidator.validate(customerWithSurnameError.getName())).thenReturn(true);
        when(surnameValidator.validate(customerWithSurnameError.getSurname())).thenReturn(false);
        when(phoneValidator.validate(customerWithSurnameError.getPhoneNumber())).thenReturn(true);
        when(emailValidator.validate(customerWithSurnameError.getEmail())).thenReturn(true);

        assertThat(false, is(userValidator.validate(customerWithSurnameError)));

    }

    @Test
    public void shouldReturnFalseIfEmailOfCustomerNotCorrect() {
        Customer customerWithEmailError = Customer.builder()
                .withName("Vova")
                .withSurname("Tsaruk")
                .withEmail("vova244g4mail.com")
                .withPhoneNumber("380779933311")
                .build();

        when(nameValidator.validate(customerWithEmailError.getName())).thenReturn(true);
        when(surnameValidator.validate(customerWithEmailError.getSurname())).thenReturn(true);
        when(phoneValidator.validate(customerWithEmailError.getPhoneNumber())).thenReturn(true);
        when(emailValidator.validate(customerWithEmailError.getEmail())).thenReturn(false);

        assertThat(false, is(userValidator.validate(customerWithEmailError)));
    }

    @Test
    public void shouldReturnFalseIfPhoneOfCustomerNotCorrect() {
        Customer customerWithPhoneError = Customer.builder()
                .withName("Vova")
                .withSurname("Tsaruk")
                .withEmail("vova@gmail.com")
                .withPhoneNumber("380d7fad3311")
                .build();


        when(nameValidator.validate(customerWithPhoneError.getName())).thenReturn(true);
        when(surnameValidator.validate(customerWithPhoneError.getSurname())).thenReturn(true);
        when(phoneValidator.validate(customerWithPhoneError.getPhoneNumber())).thenReturn(false);
        when(emailValidator.validate(customerWithPhoneError.getEmail())).thenReturn(true);

        assertThat(false, is(userValidator.validate(customerWithPhoneError)));

    }
}