package ua.mycompany.domain.order;

public class Onion extends Vegetable {
    public Onion(int calories, double weight, int price) {
        super(calories, weight, price);
    }

    @Override
    public String toString() {
        return "This is Onion{id="+ id + ", calories=" + calories + ", weight=" + weight + ", price=" + price + '}';
    }
}
