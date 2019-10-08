package ua.mycompany.domain.order;

public class Tomato extends Vegetable {
    public Tomato(int calories, double weight, int price) {
        super(calories, weight, price);
    }

    @Override
    public String toString() {
        return "This is Tomato{" + "calories=" + calories + ", weight=" + weight + ", price=" + price + '}';
    }
}

