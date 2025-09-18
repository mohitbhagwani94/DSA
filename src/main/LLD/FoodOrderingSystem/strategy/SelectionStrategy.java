package FoodOrderingSystem.strategy;

import FoodOrderingSystem.model.Order;
import FoodOrderingSystem.model.Restaurant;
import java.util.List;
import java.util.Optional;

public interface SelectionStrategy {
    Optional<Restaurant> selectRestaurant(Order order, List<Restaurant> eligibleRestaurants);
    String name();
}
