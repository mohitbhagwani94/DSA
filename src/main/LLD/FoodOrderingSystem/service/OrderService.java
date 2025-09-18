package FoodOrderingSystem.service;

import FoodOrderingSystem.model.Order;
import FoodOrderingSystem.model.OrderStatus;
import FoodOrderingSystem.model.Restaurant;
import FoodOrderingSystem.repository.InMemoryOrderRepository;
import FoodOrderingSystem.repository.InMemoryRestaurantRepository;
import FoodOrderingSystem.strategy.SelectionStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private final InMemoryOrderRepository orderRepo;
    private final InMemoryRestaurantRepository restaurantRepo;

    public OrderService(InMemoryOrderRepository orderRepo, InMemoryRestaurantRepository restaurantRepo) {
        this.orderRepo = orderRepo;
        this.restaurantRepo = restaurantRepo;
    }

    public Order placeOrder(String user, Map<String, Integer> items, SelectionStrategy strategy) {
        Order order = new Order(user, items, strategy.name());
        List<Restaurant> eligible = restaurantRepo.findAll().stream()
                .filter(r -> r.canFulfill(items))
                .filter(r -> r.getCurrentProcessingOrders() < r.getMaxConcurrentOrders())
                .collect(Collectors.toList());

        if (eligible.isEmpty()) {
            order.setStatus(OrderStatus.REJECTED);
            orderRepo.save(order);
            return order;
        }

        Optional<Restaurant> chosenOpt = strategy.selectRestaurant(order, eligible);
        if (!chosenOpt.isPresent()) {
            order.setStatus(OrderStatus.REJECTED);
            orderRepo.save(order);
            return order;
        }

        Restaurant chosen = chosenOpt.get();
        boolean acquired = chosen.tryAcquireSlot();
        if (!acquired) {
            List<Restaurant> remaining = eligible.stream()
                    .filter(r -> !r.getName().equalsIgnoreCase(chosen.getName()))
                    .collect(Collectors.toList());
            Optional<Restaurant> fallback = strategy.selectRestaurant(order, remaining);
            if (fallback.isPresent()) {
                chosen = fallback.get();
                if (!chosen.tryAcquireSlot()) {
                    order.setStatus(OrderStatus.REJECTED);
                    orderRepo.save(order);
                    return order;
                }
            } else {
                order.setStatus(OrderStatus.REJECTED);
                orderRepo.save(order);
                return order;
            }
        }

        double cost = chosen.computeCost(items);
        order.setAssignedRestaurant(chosen.getName());
        order.setAssignedCost(cost);
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepo.save(order);
        return order;
    }

    public Order markOrderCompleted(String restaurantName, String orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        if (order.getAssignedRestaurant() == null || !order.getAssignedRestaurant().equalsIgnoreCase(restaurantName)) {
            throw new IllegalArgumentException("Order " + orderId + " is not assigned to restaurant " + restaurantName);
        }
        if (order.getStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException("Only ACCEPTED orders can be marked COMPLETED. Current status: " + order.getStatus());
        }

        Restaurant r = restaurantRepo.findByName(restaurantName)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found: " + restaurantName));

        order.setStatus(OrderStatus.COMPLETED);
        r.releaseSlot();
        orderRepo.save(order);
        return order;
    }
}
