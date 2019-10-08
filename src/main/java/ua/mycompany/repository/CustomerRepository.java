package ua.mycompany.repository;


import ua.mycompany.domain.customer.Customer;

import java.util.Optional;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

//    ArrayList<Customer> findByDepartment(Long idDepartment);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course);
}
