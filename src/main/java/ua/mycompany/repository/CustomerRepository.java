package ua.mycompany.repository;


import ua.mycompany.domain.customer.Customer;

import java.util.Optional;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
