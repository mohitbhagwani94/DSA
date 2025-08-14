package BuilderDesignPattern;

public interface Builder {
    Builder id(int id);

    Builder color(String color);

    Builder model(String model);

    Builder brand(String brand);
}
