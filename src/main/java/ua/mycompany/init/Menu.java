package ua.mycompany.init;

import ua.mycompany.domain.Address;
import ua.mycompany.domain.Customer;
import ua.mycompany.domain.Role;
import ua.mycompany.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Menu {

    private CustomerService customerService;

    @Autowired
    public Menu(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void run() {
        Customer ivan = Customer.builder()
                .withName("Ivan")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380911111111")
                .withEmail("ivan@gmail.com")
                .withPassword("ivanIVAN70")
                .withRole(Role.ADMIN)
                .build();

        Customer vasyl = Customer.builder()
                .withName("Vasyl")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 14))
                .withPhoneNumber("380922222222")
                .withEmail("vasv@gmail.com")
                .withPassword("vasVas25")
                .build();

        Customer volodymyr = Customer.builder()
                .withName("Volodymyr")
                .withSurname("Tsaruk")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380933333333")
                .withEmail("vv@gmail.com")
                .withPassword("vovaVOVA7")
                .build();

        customerService.register(ivan);
        customerService.register(vasyl);
        customerService.register(volodymyr);

    }
}
