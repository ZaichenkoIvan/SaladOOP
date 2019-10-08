package ua.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

    @Autowired
    public AdminServiceImpl(CustomerRepository customerRepository, VegetableService vegetableService) {
        super(customerRepository, vegetableService);
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
        if (customerDeleteById.isPresent()) {
            return customerDeleteById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of customer uncorrected");
    }
}