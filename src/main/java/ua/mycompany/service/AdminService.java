package ua.mycompany.service;

import ua.mycompany.domain.customer.Customer;

import java.util.ArrayList;

public interface AdminService extends UserService {
    ArrayList<Customer> findAll();

    Customer deleteById(Long id);
}
