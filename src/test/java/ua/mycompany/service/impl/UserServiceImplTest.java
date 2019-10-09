package ua.mycompany.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.mycompany.domain.customer.Address;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.domain.order.impl.Cabbage;
import ua.mycompany.domain.order.impl.Cucumber;
import ua.mycompany.exception.CustomerNotExistRuntimeException;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.repository.CustomerRepository;
import ua.mycompany.service.VegetableService;
import ua.mycompany.util.encoder.PasswordEncoder;
import ua.mycompany.util.validator.UserValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private Customer ivan;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private VegetableService vegetableService;

    @InjectMocks
    private UserServiceImpl userService;

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

    @After
    public void resetMock() {
        reset(customerRepository);
    }

    @Test
    public void shouldReturnLoginCustomer() {
        Customer studentExpected = (Customer) ivan.clone(PasswordEncoder.generateSecurePassword(ivan.getPassword()));

        when(customerRepository.findByEmail(any(String.class))).thenReturn(ofNullable(studentExpected));

        Customer studentActual = userService.login(ivan.getEmail(), ivan.getPassword());
        assertThat(true, is(PasswordEncoder.verifyCustomerPassword(ivan.getPassword(), studentActual.getPassword())));
    }

    @Test
    public void shouldReturnRegisterCustomer() {

        Customer studentExpected = (Customer) ivan.clone(PasswordEncoder.generateSecurePassword(ivan.getPassword()));

        when(customerRepository.save(any(Customer.class))).thenReturn(studentExpected);
        when(userValidator.validate(any(Customer.class))).thenReturn(true);

        Customer studentActual = userService.register(ivan);
        assertThat(true, is(PasswordEncoder.verifyCustomerPassword(ivan.getPassword(), studentActual.getPassword())));
    }

    @Test
    public void shouldReturnCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(ivan));

        Customer studentActual = userService.findById(1L);
        assertThat(ivan, is(studentActual));
    }

    @Test
    public void shouldReturnAllVegetable() {
        Cabbage cabbage = new Cabbage(100, 100.0, 100);
        Cucumber cucumber = new Cucumber(100, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(vegetableService.findById(cabbage.getId())).thenReturn(cabbage);
        when(vegetableService.findById(cucumber.getId())).thenReturn(cucumber);

        userService.addVegetable(ivan, cabbage.getId());
        userService.addVegetable(ivan, cucumber.getId());

        ArrayList<Vegetable> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(cabbage);
        expectedVegetables.add(cucumber);

        assertThat(expectedVegetables, is(userService.findAllVegetable(ivan)));
    }

    @Test
    public void shouldReturnAddingVegetable() {
        Cabbage cabbage = new Cabbage(100, 100.0, 100);
        Cucumber cucumber = new Cucumber(100, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(vegetableService.findById(cabbage.getId())).thenReturn(cabbage);
        when(vegetableService.findById(cucumber.getId())).thenReturn(cucumber);

        userService.addVegetable(ivan, cabbage.getId());
        userService.addVegetable(ivan, cucumber.getId());

        ArrayList<Vegetable> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(cabbage);
        expectedVegetables.add(cucumber);

        assertThat(expectedVegetables, is(userService.findAllVegetable(ivan)));
    }

    @Test
    public void shouldReturnDeletingVegetable() {
        Cabbage cabbage = new Cabbage(100, 100.0, 100);
        Cucumber cucumber = new Cucumber(100, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(vegetableService.findById(cabbage.getId())).thenReturn(cabbage);
        when(vegetableService.findById(cucumber.getId())).thenReturn(cucumber);

        userService.addVegetable(ivan, cabbage.getId());
        userService.addVegetable(ivan, cucumber.getId());
        userService.deleteVegetable(ivan, cucumber.getId());

        ArrayList<Vegetable> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(cabbage);

        assertThat(expectedVegetables, is(userService.findAllVegetable(ivan)));
    }

    @Test
    public void shouldReturnSortingVegetable() {
        Cabbage cabbage = new Cabbage(200, 100.0, 100);
        Cucumber cucumber = new Cucumber(100, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(vegetableService.findById(cabbage.getId())).thenReturn(cabbage);
        when(vegetableService.findById(cucumber.getId())).thenReturn(cucumber);

        userService.addVegetable(ivan, cabbage.getId());
        userService.addVegetable(ivan, cucumber.getId());

        ArrayList<Vegetable> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(cucumber);
        expectedVegetables.add(cabbage);

        assertThat(expectedVegetables, is(userService.sortSalad(ivan)));
    }

    @Test
    public void shouldReturnRangingByCaloriesVegetable() {
        Cabbage cabbage = new Cabbage(60, 100.0, 100);
        Cucumber cucumber = new Cucumber(100, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(vegetableService.findById(cabbage.getId())).thenReturn(cabbage);
        when(vegetableService.findById(cucumber.getId())).thenReturn(cucumber);

        userService.addVegetable(ivan, cabbage.getId());
        userService.addVegetable(ivan, cucumber.getId());

        ArrayList<Vegetable> expectedVegetables = new ArrayList<>();
        expectedVegetables.add(cabbage);

        assertThat(expectedVegetables, is(userService.rangeByCalories(ivan, 30, 70)));
    }

    @Test
    public void shouldReturnSummaryOfCaloriesSalad() {
        Cabbage cabbage = new Cabbage(60, 100.0, 100);
        Cucumber cucumber = new Cucumber(100, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(vegetableService.findById(cabbage.getId())).thenReturn(cabbage);
        when(vegetableService.findById(cucumber.getId())).thenReturn(cucumber);

        userService.addVegetable(ivan, cabbage.getId());
        userService.addVegetable(ivan, cucumber.getId());

        assertThat(160, is(userService.summaryOfCaloriesSalad(ivan)));
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInRegister() {
        userService.register(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInFindById() {
        userService.findById(-1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInUpdate() {
        userService.update(null);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInFindVegetable() {
        userService.findAllVegetable(null);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInAddVegetable() {
        userService.addVegetable(null, 1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInDeleteVegetable() {
        userService.deleteVegetable(null, 1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInSortVegetable() {
        userService.sortSalad(null);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInRangeVegetable() {
        userService.rangeByCalories(null, 10, 100);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInSummaryVegetable() {
        userService.summaryOfCaloriesSalad(null);
    }
}