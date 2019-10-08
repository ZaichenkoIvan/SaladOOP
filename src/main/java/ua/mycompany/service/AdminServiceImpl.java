package ua.mycompany.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.Customer;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminServiceImpl extends UserServiceImpl implements AdminService{

    @Autowired
    public AdminServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
    }

    @Override
    public ArrayList<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be positive");
        }

        Optional<Customer> CustomerFindingById = customerRepository.deleteById(id);

        if (CustomerFindingById.isPresent()) {
            return CustomerFindingById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of Customer must be correct");
    }
}
