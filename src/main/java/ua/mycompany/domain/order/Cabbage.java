package ua.mycompany.domain.order;

public class Cabbage extends Vegetable {
    public Cabbage(int calories, double weight, int price) {
        super(calories, weight, price);
    }

    @Override
    public String toString() {
        return "This is Cabbage{id="+ id + "calories=" + calories + ", weight=" + weight + ", price=" + price + '}';
    }
}
