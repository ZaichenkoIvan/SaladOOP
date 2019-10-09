package ua.mycompany.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.exception.CustomerNotExistRuntimeException;
import ua.mycompany.exception.CustomerNotValidateRuntimeException;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.UncorrectedLoginRuntimeException;
import ua.mycompany.repository.CustomerRepository;
import ua.mycompany.service.UserService;
import ua.mycompany.service.VegetableService;
import ua.mycompany.util.encoder.PasswordEncoder;
import ua.mycompany.util.validator.UserValidator;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    protected CustomerRepository customerRepository;
    private VegetableService vegetableService;
    private UserValidator userValidator;

    @Autowired
    public UserServiceImpl(CustomerRepository customerRepository, VegetableService vegetableService, UserValidator userValidator) {
        this.customerRepository = customerRepository;
        this.vegetableService = vegetableService;
        this.userValidator = userValidator;
    }

    public Customer register(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        if (!userValidator.validate(customer)) {
            throw new CustomerNotValidateRuntimeException("Customer has not validate data");
        }

        String encodePassword = PasswordEncoder.generateSecurePassword(customer.getPassword());

        Customer customerWithEncodePassword = (Customer) customer.clone(encodePassword);

        return customerRepository.save(customerWithEncodePassword);
    }

    @Override
    public Customer login(String email, String password) {
        String encodePassword = PasswordEncoder.generateSecurePassword(password);

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


        return customerFindingById.orElseThrow(() -> new UncorrectedIdRuntimeException("Id of Customer must be correct"));
    }


    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        if (!userValidator.validate(customer)) {
            throw new CustomerNotValidateRuntimeException("Customer has not validate data");
        }

        customerRepository.update(customer);
    }

    @Override
    public ArrayList<Vegetable> findAllVegetable(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        if (!userValidator.validate(customer)) {
            throw new CustomerNotValidateRuntimeException("Customer has not validate data");
        }

        return customer.getSalad().getVegetables();
    }

    @Override
    public void addVegetable(Customer customer, Long idVegetable) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        if (!userValidator.validate(customer)) {
            throw new CustomerNotValidateRuntimeException("Customer has not validate data");
        }

        Vegetable vegetable = vegetableService.findById(idVegetable);
        customer.getSalad().add(vegetable);
        update(customer);
    }

    @Override
    public void deleteVegetable(Customer customer, Long idVegetable) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        if (!userValidator.validate(customer)) {
            throw new CustomerNotValidateRuntimeException("Customer hasn't validate data");
        }

        Vegetable vegetable = vegetableService.findById(idVegetable);
        customer.getSalad().remove(vegetable);
        update(customer);
    }

    @Override
    public ArrayList<Vegetable> sortSalad(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        if (!userValidator.validate(customer)) {
            throw new CustomerNotValidateRuntimeException("Customer has not validate data");
        }

        return customer.getSalad().sort();
    }

    @Override
    public ArrayList<Vegetable> rangeByCalories(Customer customer, double startRange, double endRange) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        if (!userValidator.validate(customer)) {
            throw new CustomerNotValidateRuntimeException("Customer has not validate data");
        }

        return customer.getSalad().searchElementCalories(startRange, endRange);
    }

    @Override
    public int summaryOfCaloriesSalad(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        if (!userValidator.validate(customer)) {
            throw new CustomerNotValidateRuntimeException("Customer has not validate data");
        }

        return customer.getSalad().getSummaryOfCalories();
    }
}
