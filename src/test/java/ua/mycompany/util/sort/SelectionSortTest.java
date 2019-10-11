package ua.mycompany.util.sort;

import org.junit.Test;
import ua.mycompany.domain.customer.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SelectionSortTest {
    @Test
    public void shouldReturnSortArrayList() {
        Customer customerVova = Customer.builder()
                .withName("vova")
                .withSurname("aaaa")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .build();
        Customer customerVania = Customer.builder()
                .withName("vania")
                .withSurname("aaaa")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .build();
        Customer customerVasyl = Customer.builder()
                .withName("vasyl")
                .withSurname("aaaa")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .build();


        List<Customer> customersExpected = Arrays.asList(customerVania, customerVasyl, customerVova);
        ArrayList<Customer> customersActual = new ArrayList<>();
        customersActual.add(customerVova);
        customersActual.add(customerVania);
        customersActual.add(customerVasyl);
        SelectionSort.sort(customersActual);
        assertThat(customersExpected, is(customersActual));

    }
}