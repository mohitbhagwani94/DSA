package Test.Decorator;

public class Cheese implements Topping{
    BasePizza bp;
    Cheese(BasePizza bp){
        this.bp = bp;
    }
    @Override
    public int cost() {
        return this.bp.cost()+10;
    }
}
