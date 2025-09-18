package FoodOrderingSystem.repository;

import FoodOrderingSystem.model.Restaurant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRestaurantRepository {
    private final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();

    public void save(Restaurant r) {
        String key = r.getName().toLowerCase();
        if (restaurants.putIfAbsent(key, r) != null) {
            throw new IllegalArgumentException("Restaurant already exists: " + r.getName());
        }
    }

    public Optional<Restaurant> findByName(String name) {
        return Optional.ofNullable(restaurants.get(name.toLowerCase()));
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants.values());
    }
}
