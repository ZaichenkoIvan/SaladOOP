package ua.mycompany.service;

import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Vegetable;

import java.util.ArrayList;

public interface UserService {
    Customer register(Customer customer);

    Customer login(String email, String password);

    Customer findById(Long id);

    void update(Customer customer);

    ArrayList<Vegetable> findAllVegetable(Customer customer);

    void addVegetable(Customer customer, Long idVegetables);

    void deleteVegetable(Customer customer, Long idVegetables);

    ArrayList<Vegetable> sortSalad(Customer customer);

    ArrayList<Vegetable> rangeByCalories(Customer customer, double startRange, double endRange);

    int summaryOfCaloriesSalad(Customer customer);


}
