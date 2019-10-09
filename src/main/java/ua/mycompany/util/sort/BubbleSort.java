package ua.mycompany.util.sort;

import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;

import java.util.ArrayList;

@Component
public final class BubbleSort {

    private BubbleSort() {
    }

    public static ArrayList<Customer> sort(ArrayList<Customer> customers) {

        for (int i = 0; i < customers.size() - 1; i++) {
            for (int j = 0; j < customers.size() - i - 1; j++) {
                if (customers.get(j).compareTo(customers.get(j + 1)) > 0)
                    SwapForSorting.swap(customers, j, j + 1);
            }
        }
        return customers;
    }
}
