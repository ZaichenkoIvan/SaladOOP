package ua.mycompany.service;


import org.springframework.context.annotation.Primary;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.UncorrectedLoginRuntimeException;
import ua.mycompany.exception.CustomerNotExistRuntimeException;
import ua.mycompany.helper.utility.PasswordUtils;
import ua.mycompany.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Optional<Customer> CustomerFindingById = customerRepository.findById(id);

        if (CustomerFindingById.isPresent()) {
            return CustomerFindingById.get();
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

//    @Override
//    public ArrayList<Customer> findByDepartment(Long idDepartment) {
//        if (idDepartment < 0) {
//            throw new UncorrectedIdRuntimeException("Id must be positive");
//        }
//
//        return customerRepository.findByDepartment(idDepartment);
//
//
//    }
//
//    @Override
//    public ArrayList<Customer> findByYear(int year) {
//        if (year < 1900) {
//            throw new IllegalArgumentException("Year must be >1900");
//        }
//
//        return customerRepository.findByYear(year);
//    }
//
//    @Override
//    public ArrayList<Customer> findByGroup(String group) {
//        if (group == null) {
//            throw new IllegalArgumentException("Group is not null");
//        }
//        return customerRepository.findByGroup(group);
//    }
//
//    @Override
//    public ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course) {
//        if (course < 0 || course > 6 || idDepartment < 0) {
//            throw new IllegalArgumentException("Course must be in range [0;6] or id department must be possitive");
//        }
//        return customerRepository.findByDepartmentAndCourse(idDepartment, course);
//    }
//}
