package ua.mycompany.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.exception.CustomerNotExistRuntimeException;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.UncorrectedLoginRuntimeException;
import ua.mycompany.exception.VegetableNotExistRuntimeException;
import ua.mycompany.helper.utility.PasswordUtils;
import ua.mycompany.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    protected CustomerRepository customerRepository;
    private VegetableService vegetableService;

    @Autowired
    public UserServiceImpl(CustomerRepository customerRepository, VegetableService vegetableService) {
        this.customerRepository = customerRepository;
        this.vegetableService = vegetableService;
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

    @Override
    public ArrayList<Vegetable> findAllVegetable(Customer customer){
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        return customer.getSalad().getVegetables();
    }

    @Override
    public void addVegetable(Customer customer, Long idVegetable) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
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

        Vegetable vegetable = vegetableService.findById(idVegetable);
        customer.getSalad().remove(vegetable);
        update(customer);
    }

    @Override
    public ArrayList<Vegetable> sortSalad(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        return customer.getSalad().sort();
    }

    @Override
    public ArrayList<Vegetable> rangeByCalories(Customer customer, double startRange, double endRange) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        return customer.getSalad().searchElementCalories(startRange, endRange);
    }

    @Override
    public int summaryOfCaloriesSalad(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer not exist");
        }

        return customer.getSalad().getSummaryOfCalories();
    }
}
