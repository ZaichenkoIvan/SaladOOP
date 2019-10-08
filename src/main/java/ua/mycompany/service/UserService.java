package ua.mycompany.service;

import ua.mycompany.domain.Customer;

import java.util.ArrayList;

public interface UserService {
    Customer register(Customer customer);

    Customer login (String email, String password);

    Customer findById(Long id);

    void update(Customer customer);

//    ArrayList<Customer> findByDepartment(Long idDepartment);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course);
}
