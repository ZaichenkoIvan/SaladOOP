package ua.mycompany.domain.order;

public class Carrot extends Vegetable {
    public Carrot(int calories, double weight, int price) {
        super(calories, weight, price);
    }

    @Override
    public String toString() {
        return "This is Carrot{" + "calories=" + calories + ", weight=" + weight + ", price=" + price + '}';
    }
}
