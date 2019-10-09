package ua.mycompany.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.mycompany.domain.customer.Address;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.repository.CustomerRepository;
import ua.mycompany.service.VegetableService;
import ua.mycompany.util.validator.UserValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

    private Customer ivan;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private VegetableService vegetableService;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Before
    public void setUp() {
        ivan = Customer.builder()
                .withName("Ivan")
                .withSurname("Zaichenk")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380911111111")
                .withEmail("1@gmail.com")
                .withPassword("1")
                .withRole(Role.ADMIN)
                .build();
    }

    @Test
    public void findAll() {
        Customer vasyl = Customer.builder()
                .withName("VasyL")
                .withSurname("Zaichenk")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380911111111")
                .withEmail("1@gmail.com")
                .withPassword("1")
                .withRole(Role.ADMIN)
                .build();

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(ivan);
        customers.add(vasyl);

        when(customerRepository.findAll()).thenReturn(customers);
        assertThat(customers, is(adminService.findAll()));
    }

    @Test
    public void shouldReturnDeleteCustomer() {
        when(customerRepository.deleteById(ivan.getId())).thenReturn(Optional.ofNullable(ivan));

        Customer studentActual = adminService.deleteById(ivan.getId());
        assertThat(ivan, is(studentActual));
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInFindById() {
        adminService.findById(-1L);
    }

}