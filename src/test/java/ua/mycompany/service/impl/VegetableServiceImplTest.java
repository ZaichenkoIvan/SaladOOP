package ua.mycompany.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.domain.order.impl.Cucumber;
import ua.mycompany.domain.order.impl.Tomato;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.VegetableNotExistRuntimeException;
import ua.mycompany.repository.VegetableRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
    public void shouldReturnSavedVegetable() {
        when(vegetableRepository.save(cucumber)).thenReturn(cucumber);

        Vegetable cucumberActual = vegetableService.save(cucumber);
        assertThat(cucumber, is(cucumberActual));
    }

    @Test
    public void shouldReturnVegetableById() {
        when(vegetableRepository.findById(1L)).thenReturn(Optional.ofNullable(cucumber));

        Vegetable cucumberActual = vegetableService.findById(1L);
        assertThat(cucumber, is(cucumberActual));
    }

    @Test
    public void shouldReturnAllVegetable() {
        Vegetable tomato = new Tomato(50,50,50);
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        vegetables.add(cucumber);
        vegetables.add(tomato);

        when(vegetableRepository.findAll()).thenReturn(vegetables);

        ArrayList<Vegetable> vegetablesActual = vegetableService.findAll();
        assertThat(vegetables, is(vegetablesActual));
    }

    @Test
    public void shouldReturnDeletedVegetableById() {
        when(vegetableRepository.deleteById(1L)).thenReturn(Optional.ofNullable(cucumber));

        Vegetable cucumberActual = vegetableService.deleteById(1L);
        assertThat(cucumber, is(cucumberActual));
    }

    @Test(expected = VegetableNotExistRuntimeException.class)
    public void shouldReturnVegetableNotExistRuntimeExceptionInSave() {
        vegetableService.save(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInFindById() {
        vegetableService.findById(-1L);
    }

    @Test(expected = VegetableNotExistRuntimeException.class)
    public void shouldReturnVegetableNotExistRuntimeExceptionInUpdate() {
        vegetableService.update(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInDeleteById() {
        vegetableService.deleteById(-1L);
    }

}