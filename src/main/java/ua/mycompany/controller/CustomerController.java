package ua.mycompany.controller;

import ua.mycompany.domain.Customer;
import ua.mycompany.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer register(Customer customer) {
        return customerService.register(customer);
    }

    public Customer login(String email, String password) {
        return customerService.login(email, password);
    }

    public Customer findById(Long id) {
        return customerService.findById(id);
    }

    public ArrayList<Customer> findAll() {
        return customerService.findAll();
    }

    public void update(Customer customer) {
        customerService.update(customer);
    }

    public Customer deleteById(Long id) {
        return customerService.deleteById(id);
    }

//    public ArrayList<Customer> findByDepartment(Long idDepartment) {
//        return customerService.findByDepartment(idDepartment);
//    }
//
//    public ArrayList<Customer> findByYear(int year) {
//        return customerService.findByYear(year);
//    }
//
//    public ArrayList<Customer> findByGroup(String group) {
//        return customerService.findByGroup(group);
//    }
//
//    public ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course) {
//        return customerService.findByDepartmentAndCourse(idDepartment, course);
//    }
}
