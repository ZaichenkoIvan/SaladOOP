package ua.mycompany.domain.order;

import ua.mycompany.exception.VegetableUncorrectedDataRuntimeException;

import java.util.Objects;

public abstract class Vegetable implements Comparable<Vegetable> {
    protected final int calories;
    protected final double weight;
    protected final int price;
    protected final Long id;
    protected static Long counter = 0L;

    public Vegetable(int calories, double weight, int price) {
        if (calories < 0 || weight < 0 || price < 0) {
            throw new VegetableUncorrectedDataRuntimeException("Data for Vegetable isn't correct");
        }

        this.calories = calories;
        this.weight = weight;
        this.price = price;
        this.id = ++counter;
    }

    public Long getId() {
        return id;
    }

    public int getCalories() {
        return calories;
    }

    public double getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vegetable that = (Vegetable) o;
        return calories == that.calories &&
                Double.compare(that.weight, weight) == 0 &&
                price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(calories, weight, price);
    }

    public abstract String toString();

    @Override
    public int compareTo(Vegetable vegetable) {
        return this.calories - vegetable.calories;
    }
}
