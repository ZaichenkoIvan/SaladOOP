package ua.mycompany.domain.order.impl;

import ua.mycompany.domain.order.Vegetable;

public class Tomato extends Vegetable {
    public Tomato(int calories, double weight, int price) {
        super(calories, weight, price);
    }

    @Override
    public String toString() {
        return "This is Tomato{id=" + id + ", calories=" + calories + ", weight=" + weight + ", price=" + price + '}';
    }
}
