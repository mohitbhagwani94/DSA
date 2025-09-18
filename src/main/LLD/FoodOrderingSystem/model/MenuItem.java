package FoodOrderingSystem.model;

import java.util.Objects;

public class MenuItem {
    private final String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = Objects.requireNonNull(name).trim();
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ": Rs." + price;
    }
}
