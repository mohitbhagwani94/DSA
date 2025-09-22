package Test.Decorator;

public class Paneer implements Topping{
    BasePizza bp;
    Paneer(BasePizza bp){
        this.bp = bp;
    }

    @Override
    public int cost() {
        return this.bp.cost()+20;
    }
}
