package ua.mycompany.util.encoder;

import org.junit.Test;
import ua.mycompany.domain.customer.Customer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PasswordEncoderTest {
    @Test
    public void shouldReturnTrueWhenPasswordEncode() {

        Customer customerWithoutEncodePassword = Customer.builder()
                .withPassword("12345")
                .build();

        Customer customerActual = (Customer) customerWithoutEncodePassword
                .clone(PasswordEncoder.generateSecurePassword(customerWithoutEncodePassword.getPassword()));
        assertThat(true, is(PasswordEncoder
                .verifyCustomerPassword(customerWithoutEncodePassword.getPassword(), customerActual.getPassword())));
    }

}