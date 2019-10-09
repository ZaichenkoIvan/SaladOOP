package ua.mycompany.util.sort;

import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;

import java.util.ArrayList;
import java.util.Collections;

@Component
final class SwapForSorting {
    private SwapForSorting() {
    }

    public static void swap(ArrayList<Customer> customers, int i, int j) {
        Collections.swap(customers, i, j);
    }
}
