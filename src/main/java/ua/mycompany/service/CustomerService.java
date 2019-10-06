package ua.mycompany.service;

import ua.mycompany.domain.Customer;

import java.util.ArrayList;

public interface CustomerService {
    Customer register(Customer customer);

    Customer login (String email, String password);

    Customer findById(Long id);

    ArrayList<Customer> findAll();

    void update(Customer customer);

    Customer deleteById(Long id);

//    ArrayList<Customer> findByDepartment(Long idDepartment);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course);
}
