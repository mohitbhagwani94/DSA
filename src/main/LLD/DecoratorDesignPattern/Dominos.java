package DecoratorDesignPattern;

public class Dominos {
    public static void main(String[] args) {
        PizzaBase margheritaWithExtraCheesePizza = new ExtraCheese(new Margherita());
        System.out.println("Your Pizza cost is : "+margheritaWithExtraCheesePizza.cost());

        PizzaBase margheritaWithExtraCheesePizzaWithMushroom = new Mushroom(new ExtraCheese(new Margherita()));
        System.out.println("Your Pizza cost is : "+margheritaWithExtraCheesePizzaWithMushroom.cost());
    }
}
