package ua.mycompany.domain.order;

import ua.mycompany.exception.ActionSaladWithNullRuntimeException;
import ua.mycompany.exception.RangeUncorrectedRuntimeException;

import java.util.ArrayList;
import java.util.Collections;

public class Salad {
    private ArrayList<Vegetable> vegetables;

    public Salad() {
        vegetables = new ArrayList<>();
    }

    public Salad(ArrayList<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public ArrayList<Vegetable> getVegetables() {
        return vegetables;
    }

    public void add(Vegetable vegetable) {
        if (vegetable == null) {
            throw new ActionSaladWithNullRuntimeException("Add null to Salad");
        }

        vegetables.add(vegetable);
    }

    public void remove(Vegetable vegetable) {
        if (vegetable == null) {
            throw new ActionSaladWithNullRuntimeException("Remove null from Salad");
        }

        vegetables.remove(vegetable);
    }

    public ArrayList<Vegetable> sort() {
        Collections.sort(vegetables);
        return this.vegetables;
    }

    public int getSummaryOfCalories() {
        int result = 0;
        for (Vegetable vegetable : vegetables) {
            result += vegetable.getCalories();
        }
        return result;
    }

    public ArrayList<Vegetable> searchElementCalories(double startRange, double endRange) {
        if (startRange > endRange || startRange < 0) {
            throw new RangeUncorrectedRuntimeException("Range isn't correct");
        }

        ArrayList<Vegetable> searchElementCalories = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            if (vegetable.getCalories() > startRange && vegetable.getCalories() < endRange) {
                searchElementCalories.add(vegetable);
            }
        }

        return searchElementCalories;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Salad {");
        for (Vegetable vegetable : vegetables
        ) {
            result.append(vegetable.toString());
        }
        result.append("}");
        return result.toString();
    }
}
