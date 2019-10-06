package ua.mycompany.repository;


import ua.mycompany.domain.Customer;

import java.util.ArrayList;
import java.util.Optional;


public interface CustomerRepository {
    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    ArrayList<Customer> findAll();

    void update(Customer customer);

    Optional<Customer> deleteById(Long id);

    Optional<Customer> findByEmail(String email);

//    ArrayList<Customer> findByDepartment(Long idDepartment);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course);
}
