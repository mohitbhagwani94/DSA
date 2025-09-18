package FoodOrderingSystem.strategy;

import FoodOrderingSystem.model.Order;
import FoodOrderingSystem.model.Restaurant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LowestCostStrategy implements SelectionStrategy {
    @Override
    public Optional<Restaurant> selectRestaurant(Order order, List<Restaurant> eligibleRestaurants) {
        return eligibleRestaurants.stream()
                .min(Comparator.comparingDouble(r -> r.computeCost(order.getItems())));
    }

    @Override
    public String name() { return "LowestCost"; }
}
