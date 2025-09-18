package FoodOrderingSystem.repository;

import FoodOrderingSystem.model.Order;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryOrderRepository {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}
