package ua.mycompany.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Address;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;
import ua.mycompany.domain.order.impl.*;
import ua.mycompany.service.UserService;
import ua.mycompany.service.VegetableService;

import java.time.LocalDate;

@Component
public class Menu {

    private UserService userService;
    private VegetableService vegetableService;

    @Autowired
    public Menu(UserService userService, VegetableService vegetableService) {
        this.userService = userService;
        this.vegetableService = vegetableService;
    }

    public void run() {
        Customer ivan = Customer.builder()
                .withName("Ivan")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380911111111")
                .withEmail("1@gmail.com")
                .withPassword("1")
                .withRole(Role.ADMIN)
                .build();

        Customer vasyl = Customer.builder()
                .withName("Vasyl")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 14))
                .withPhoneNumber("380922222222")
                .withEmail("2@gmail.com")
                .withPassword("2")
                .build();

        Customer volodymyr = Customer.builder()
                .withName("Volodymyr")
                .withSurname("Tsaruk")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380933333333")
                .withEmail("3@gmail.com")
                .withPassword("3")
                .build();

        userService.register(ivan);
        userService.register(vasyl);
        userService.register(volodymyr);

        Cabbage cabbage = new Cabbage(100, 100.0, 100);
        Carrot carrot = new Carrot(50, 50.0, 50);
        Cucumber cucumber = new Cucumber(70, 70.0, 70);
        Onion onion = new Onion(40, 40.0, 40);
        Tomato tomato = new Tomato(90, 90.0, 90);

        vegetableService.save(cabbage);
        vegetableService.save(carrot);
        vegetableService.save(cucumber);
        vegetableService.save(onion);
        vegetableService.save(tomato);
    }
}
