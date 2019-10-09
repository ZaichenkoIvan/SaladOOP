package ua.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.service.UserService;

import java.util.ArrayList;

@Component
@Primary
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Customer register(Customer customer) {
        return userService.register(customer);
    }

    public Customer login(String email, String password) {
        return userService.login(email, password);
    }

    public Customer findById(Long id) {
        return userService.findById(id);
    }

    public void update(Customer customer) {
        userService.update(customer);
    }

    public ArrayList<Vegetable> findAllVegetable(Customer customer) {
        return userService.findAllVegetable(customer);
    }

    public void addVegetable(Customer customer, Long idVegetable) {
        userService.addVegetable(customer, idVegetable);
    }

    public void deleteVegetable(Customer customer, Long idVegetable) {
        userService.deleteVegetable(customer, idVegetable);
    }

    public ArrayList<Vegetable> sortSalad(Customer customer) {
        return userService.sortSalad(customer);
    }

    public ArrayList<Vegetable> rangeByCalories(Customer customer, double startRange, double endRange) {
        return userService.rangeByCalories(customer, startRange, endRange);
    }

    public int summaryOfCaloriesSalad(Customer customer) {
        return userService.summaryOfCaloriesSalad(customer);
    }

}
