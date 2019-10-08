package ua.mycompany.domain.order;

public class Cucumber extends Vegetable {
    public Cucumber(int calories, double weight, int price) {
        super(calories, weight, price);
    }

    @Override
    public String toString() {
        return "This is Cucumber{" + "calories=" + calories + ", weight=" + weight + ", price=" + price + '}';
    }
}
