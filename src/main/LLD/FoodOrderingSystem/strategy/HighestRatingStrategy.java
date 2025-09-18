package FoodOrderingSystem.strategy;

import FoodOrderingSystem.strategy.SelectionStrategy;
import FoodOrderingSystem.model.Order;
import FoodOrderingSystem.model.Restaurant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HighestRatingStrategy implements SelectionStrategy {
    @Override
    public Optional<Restaurant> selectRestaurant(Order order, List<Restaurant> eligibleRestaurants) {
        return eligibleRestaurants.stream()
                .max(Comparator.comparingDouble((Restaurant r) -> r.getRating())
                        .thenComparingDouble(r -> -r.computeCost(order.getItems())));
    }

    @Override
    public String name() { return "HighestRating"; }
}
