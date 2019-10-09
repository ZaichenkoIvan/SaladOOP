package ua.mycompany.domain.order.impl;

import ua.mycompany.domain.order.Vegetable;

public class Carrot extends Vegetable {
    public Carrot(int calories, double weight, int price) {
        super(calories, weight, price);
    }

    @Override
    public String toString() {
        return "This is Carrot{id=" + id + ", calories=" + calories + ", weight=" + weight + ", price=" + price + '}';
    }
}
