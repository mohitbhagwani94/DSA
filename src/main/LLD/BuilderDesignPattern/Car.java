package BuilderDesignPattern;

public class Car {
    private final int id;
    private final String brand;
    private final String color;
    private final String model;

    Car(int id, String brand, String model, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return "id=" + this.id + " ,brand=" + this.brand + " ,color=" + color;
    }
}
