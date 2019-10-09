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
import ua.mycompany.domain.order.impl.Tomato;
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
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.domain.order.impl.Cucumber;
import ua.mycompany.repository.VegetableRepository;
import ua.mycompany.service.VegetableService;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VegetableServiceImplTest {

    @Mock
    private VegetableRepository vegetableRepository;

    @InjectMocks
    private VegetableServiceImpl vegetableService;

    private Vegetable cucumber;

    @Before
    public void setUp(){
        cucumber = new Cucumber(100,100,100);
    }

    @Test
    public void save() {
        when(vegetableRepository.save(cucumber)).thenReturn(cucumber);

        Vegetable cucumberActual = vegetableService.save(cucumber);
        assertThat(cucumber, is(cucumberActual));
    }

    @Test
    public void findById() {
        when(vegetableRepository.findById(1L)).thenReturn(Optional.ofNullable(cucumber));

        Vegetable cucumberActual = vegetableService.findById(1L);
        assertThat(cucumber, is(cucumberActual));
    }

    @Test
    public void findAll() {
        Vegetable tomato = new Tomato(50,50,50);
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        

        when(vegetableRepository.findAll()).thenReturn(cucumber);

        Vegetable cucumberActual = vegetableService.save(cucumber);
        assertThat(cucumber, is(cucumberActual));
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }
}