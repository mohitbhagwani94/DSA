package FoodOrderingSystem.service;

import FoodOrderingSystem.model.MenuItem;
import FoodOrderingSystem.model.Restaurant;
import FoodOrderingSystem.repository.InMemoryRestaurantRepository;
import java.util.Collection;
import java.util.List;

public class RestaurantService {
    private final InMemoryRestaurantRepository repo;

    public RestaurantService(InMemoryRestaurantRepository repo) {
        this.repo = repo;
    }

    public void onboardRestaurant(String name, Collection<MenuItem> menu, double rating, int maxConcurrentOrders) {
        Restaurant r = new Restaurant(name, menu, rating, maxConcurrentOrders);
        repo.save(r);
    }

    public void addMenuItem(String restaurantName, MenuItem item) {
        Restaurant r = repo.findByName(restaurantName)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found: " + restaurantName));
        r.addMenuItem(item);
    }

    public void updateMenuItemPrice(String restaurantName, String itemName, double price) {
        Restaurant r = repo.findByName(restaurantName)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found: " + restaurantName));
        r.updateMenuItemPrice(itemName, price);
    }

    public List<Restaurant> listAll() {
        return repo.findAll();
    }
}
