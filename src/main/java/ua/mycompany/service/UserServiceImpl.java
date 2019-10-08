package ua.mycompany.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.exception.CustomerNotExistRuntimeException;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.UncorrectedLoginRuntimeException;
import ua.mycompany.helper.utility.PasswordUtils;
import ua.mycompany.repository.CustomerRepository;

import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    protected CustomerRepository customerRepository;

    @Autowired
    public UserServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer register(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        String encodePassword = PasswordUtils.generateSecurePassword(customer.getPassword());

        Customer customerWithEncodePassword = (Customer) customer.clone(encodePassword);

        return customerRepository.save(customerWithEncodePassword);
    }

    @Override
    public Customer login(String email, String password) {
        String encodePassword = PasswordUtils.generateSecurePassword(password);

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UncorrectedLoginRuntimeException("Login are not exist"));

        String CustomerPassword = customer.getPassword();

        if (CustomerPassword.equals(encodePassword)) {
            return customer;
        }
        throw new UncorrectedLoginRuntimeException("Password is uncorrected");
    }

    @Override
    public Customer findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of Customer must be positive");
        }

        Optional<Customer> customerFindingById = customerRepository.findById(id);

        if (customerFindingById.isPresent()) {
            return customerFindingById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of Customer must be correct");
    }

    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }
        customerRepository.update(customer);
    }

}
