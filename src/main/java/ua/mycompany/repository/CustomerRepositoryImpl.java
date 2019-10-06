package ua.mycompany.repository;

import ua.mycompany.domain.Customer;
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
    public void update(Customer customer) {
        idToCustomers.replace(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> deleteById(Long id) {
        return Optional.ofNullable(idToCustomers.remove(id));
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
//
//    public ArrayList<Customer> findByDepartment(Long idDepartment) {
//        ArrayList<Customer> findByFacultyCustomers = new ArrayList<>();
//        for (Long i = 1L; i < idToCustomers.size() + 1; i++) {
//            if (idDepartment.equals(idToCustomers.get(i).getDepartment().getId())) {
//                findByFacultyCustomers.add(idToCustomers.get(i));
//            }
//        }
//        return findByFacultyCustomers;
//    }
//
//    public ArrayList<Customer> findByYear(int year) {
//        ArrayList<Customer> findByYearCustomers = new ArrayList<>();
//        for (Long i = 1L; i < idToCustomers.size() + 1; i++) {
//            if (year < idToCustomers.get(i).getBirthday().getYear()) {
//                findByYearCustomers.add(idToCustomers.get(i));
//            }
//        }
//        return findByYearCustomers;
//    }
//
//    @Override
//    public ArrayList<Customer> findByGroup(String group) {
//        ArrayList<Customer> findByGroupCustomers = new ArrayList<>();
//        for (Long i = 1L; i < idToCustomers.size() + 1; i++) {
//            if (group.equals(idToCustomers.get(i).getGroup())) {
//                findByGroupCustomers.add(idToCustomers.get(i));
//            }
//        }
//        return findByGroupCustomers;
//    }
//
//    public ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int cource) {
//        ArrayList<Customer> findByDepartmentAndCourseCustomers = new ArrayList<>();
//        for (Long i = 1L; i < idToCustomers.size() + 1; i++) {
//            if (idDepartment.equals(idToCustomers.get(i).getDepartment().getId())
//                    && cource == idToCustomers.get(i).getCourse()) {
//                findByDepartmentAndCourseCustomers.add(idToCustomers.get(i));
//            }
//        }
//        return findByDepartmentAndCourseCustomers;
//    }
}
