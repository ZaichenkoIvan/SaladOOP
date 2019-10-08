package ua.mycompany.repository;

import ua.mycompany.domain.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private Map<Long, Customer> idToCustomers = new HashMap<>();
    private static Long counter = 0L;

    @Autowired
    public CustomerRepositoryImpl() {
    }

    @Override
    public Customer save(Customer customer) {
        return idToCustomers.put(++counter, customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(idToCustomers.get(id));
    }

    @Override
    public ArrayList<Customer> findAll() {
        return new ArrayList<>(idToCustomers.values());
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        Customer customer = null;
        for (Long i = 1L; i < idToCustomers.size() + 1; i++) {
            if (idToCustomers.get(i).getEmail().equals(email)) {
                customer = idToCustomers.get(i);
                break;
            }
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public void update(Customer customer) {
        idToCustomers.replace(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> deleteById(Long id) {
        return Optional.ofNullable(idToCustomers.remove(id));
    }
}
