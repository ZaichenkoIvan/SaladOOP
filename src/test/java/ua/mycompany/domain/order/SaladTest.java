package ua.mycompany.domain.order;

import org.junit.Before;
import org.junit.Test;
import ua.mycompany.domain.order.impl.Cabbage;
import ua.mycompany.domain.order.impl.Cucumber;
import ua.mycompany.domain.order.impl.Tomato;
import ua.mycompany.exception.ActionSaladWithNullRuntimeException;
import ua.mycompany.exception.RangeUncorrectedRuntimeException;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SaladTest {
    private Salad salad;
    private Cucumber cucumber;
    private Tomato tomato;

    @Before
    public void setUp() {
        salad = new Salad();
        cucumber = new Cucumber(100,100,100);
        tomato = new Tomato(50,50,50);
        salad.add(cucumber);
        salad.add(tomato);
    }

    @Test
    public void shouldReturnAddingVegetable() {
        Cabbage cabbage = new Cabbage(70,70,70);
        salad.add(cabbage);
        assertThat(salad.getVegetables(), hasItem(cabbage));
    }

    @Test
    public void shouldReturnDeleteVegetable() {
        Cabbage cabbage = new Cabbage(70,70,70);
        salad.add(cabbage);
        salad.remove(cabbage);
        assertThat(salad.getVegetables(), not(cabbage));
    }

    @Test
    public void shouldReturnSortingVegetable() {
        Cabbage cabbage = new Cabbage(70,70,70);
        salad.add(cabbage);

        ArrayList<Vegetable> vegetables = new ArrayList<>();
        vegetables.add(tomato);
        vegetables.add(cabbage);
        vegetables.add(cucumber);

        assertThat(vegetables, is(salad.sort()));
    }

    @Test
    public void shouldReturnSummaryOfCalories() {
        Cabbage cabbage = new Cabbage(70,70,70);
        salad.add(cabbage);
        assertThat(220, is(salad.getSummaryOfCalories()));
    }

    @Test
    public void shouldReturnSearchElementCalories() {
        Cabbage cabbage = new Cabbage(70,70,70);
        salad.add(cabbage);

        ArrayList<Vegetable> vegetables = new ArrayList<>();
        vegetables.add(cucumber);
        vegetables.add(cabbage);

        assertThat(vegetables, is(salad.searchElementCalories(60,120)));
    }

    @Test(expected = RangeUncorrectedRuntimeException.class)
    public void shouldReturnRangeUncorrectedRuntimeException() {
        salad.searchElementCalories(-100,-100);
    }

    @Test(expected = ActionSaladWithNullRuntimeException.class)
    public void shouldReturnActionSaladWithNullRuntimeException() {
        salad.add(null);
    }
}