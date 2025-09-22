package Test.Decorator;

public class Demo {
    public static void main(String ags[]) {
        BasePizza plainPizzaWithExtraCheese = new Cheese(new Plainbase());
        System.out.println(plainPizzaWithExtraCheese.cost());
    }
}
