package ua.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.service.AdminService;

import java.util.ArrayList;

@Component
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public Customer register(Customer customer) {
        return adminService.register(customer);
    }

    public Customer login(String email, String password) {
        return adminService.login(email, password);
    }

    public Customer findById(Long id) {
        return adminService.findById(id);
    }

    public ArrayList<Customer> findAll() {
        return adminService.findAll();
    }

    public void update(Customer customer) {
        adminService.update(customer);
    }

    public Customer deleteById(Long id) {
        return adminService.deleteById(id);
    }

    public ArrayList<Vegetable> findAllVegetable(Customer customer) {
        return adminService.findAllVegetable(customer);
    }

    public void addVegetable(Customer customer, Long idVegetable) {
        adminService.addVegetable(customer, idVegetable);
    }

    public void deleteVegetable(Customer customer, Long idVegetable) {
        adminService.deleteVegetable(customer, idVegetable);
    }

    public ArrayList<Vegetable> sortSalad(Customer customer) {
        return adminService.sortSalad(customer);
    }

    public ArrayList<Vegetable> rangeByCalories(Customer customer, double startRange, double endRange) {
        return adminService.rangeByCalories(customer, startRange, endRange);
    }

    public int summaryOfCaloriesSalad(Customer customer) {
        return adminService.summaryOfCaloriesSalad(customer);
    }
}
