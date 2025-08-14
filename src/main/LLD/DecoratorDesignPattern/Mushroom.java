package DecoratorDesignPattern;

public class Mushroom extends ToppingDecorator{
    PizzaBase bp;
    public Mushroom(PizzaBase pizza){
        this.bp = pizza;
    }

    @Override
    public int cost() {
        return this.bp.cost()+25;
    }
}
