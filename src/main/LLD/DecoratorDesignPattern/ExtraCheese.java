package DecoratorDesignPattern;

public class ExtraCheese extends ToppingDecorator{
    PizzaBase pb;

    public ExtraCheese(PizzaBase pizza){
        this.pb =pizza;
    }

    @Override
    public int cost() {
        return this.pb.cost()+10;
    }
}
