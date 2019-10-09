package ua.mycompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.repository.CustomerRepository;
import ua.mycompany.service.AdminService;
import ua.mycompany.service.VegetableService;
import ua.mycompany.util.validator.UserValidator;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

    @Autowired
    public AdminServiceImpl(CustomerRepository customerRepository, VegetableService vegetableService,
                            UserValidator userValidator) {
        super(customerRepository, vegetableService, userValidator);
    }

    @Override
    public ArrayList<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of customer must be > 0");
        }

        Optional<Customer> customerDeleteById = customerRepository.deleteById(id);
        return customerDeleteById.orElseThrow(() -> new UncorrectedIdRuntimeException("Id of Customer must be correct"));
    }
}
