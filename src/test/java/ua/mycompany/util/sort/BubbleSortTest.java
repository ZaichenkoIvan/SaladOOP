package ua.mycompany.util.sort;

import org.junit.Test;
import ua.mycompany.domain.customer.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {

    @Test
    public void shouldReturnSortArrayList() {
        Customer customerVova = Customer.builder()
                .withName("Vova")
                .withSurname("Aaaa")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .build();
        Customer customerVania = Customer.builder()
                .withName("Vania")
                .withSurname("Aaaa")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .build();
        Customer customerVasyl = Customer.builder()
                .withName("Vasyl")
                .withSurname("Aaaa")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .build();


        List<Customer> customersExpected = Arrays.asList(customerVania, customerVasyl, customerVova);
        ArrayList<Customer> customersActual = new ArrayList<>();
        customersActual.add(customerVova);
        customersActual.add(customerVania);
        customersActual.add(customerVasyl);
        BubbleSort.sort(customersActual);
        assertThat(customersExpected, is(customersActual));

    }
}