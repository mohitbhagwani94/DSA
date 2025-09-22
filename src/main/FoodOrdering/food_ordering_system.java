// File: model/MenuItem.java
package foodordersystem.model;

public class MenuItem {
    private final String name;
    private volatile double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() { return name + ": Rs." + price; }
}

// File: model/Restaurant.java
package foodordersystem.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
    private final String name;
    private final Map<String, MenuItem> menu = new HashMap<>();
    private volatile double rating; // 1.0 - 5.0
    private final int maxConcurrentOrders;

    // concurrency helpers
    private final AtomicInteger currentProcessing = new AtomicInteger(0);
    private final ReentrantLock lock = new ReentrantLock();

    public Restaurant(String name, int maxConcurrentOrders, double rating) {
        this.name = name;
        this.maxConcurrentOrders = maxConcurrentOrders;
        this.rating = rating;
    }

    public String getName() { return name; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public int getMaxConcurrentOrders() { return maxConcurrentOrders; }

    public void addMenuItem(String itemName, double price) {
        menu.putIfAbsent(itemName.toLowerCase(), new MenuItem(itemName, price));
    }

    public void updateMenuItemPrice(String itemName, double newPrice) {
        MenuItem item = menu.get(itemName.toLowerCase());
        if (item == null) throw new IllegalArgumentException("Item not found: " + itemName);
        item.setPrice(newPrice);
    }

    public Optional<MenuItem> getMenuItem(String itemName) {
        return Optional.ofNullable(menu.get(itemName.toLowerCase()));
    }

    public boolean hasAllItems(Map<String, Integer> requestedItems) {
        for (String item : requestedItems.keySet()) {
            if (!menu.containsKey(item.toLowerCase())) return false;
        }
        return true;
    }

    public double computeCost(Map<String, Integer> requestedItems) {
        double total = 0.0;
        for (Map.Entry<String, Integer> e : requestedItems.entrySet()) {
            MenuItem mi = menu.get(e.getKey().toLowerCase());
            total += mi.getPrice() * e.getValue();
        }
        return total;
    }

    public boolean tryAcquireSlot() {
        while (true) {
            int curr = currentProcessing.get();
            if (curr >= maxConcurrentOrders) return false;
            if (currentProcessing.compareAndSet(curr, curr + 1)) return true;
        }
    }

    public void releaseSlot() {
        currentProcessing.decrementAndGet();
    }

    public int getCurrentProcessing() { return currentProcessing.get(); }

    public ReentrantLock getLock() { return lock; }

    @Override
    public String toString() {
        return "Restaurant{" + name + ", rating=" + rating + ", menu=" + menu.values() + ", processing=" + currentProcessing + ", max=" + maxConcurrentOrders + "}";
    }
}

// File: model/Order.java
package foodordersystem.model;

import java.util.*;

public class Order {
    public enum Status { CREATED, ASSIGNED, ACCEPTED, COMPLETED, REJECTED }

    private static long counter = 0;

    private final long id;
    private final String user;
    private final Map<String, Integer> items; // itemName -> qty
    private volatile Status status = Status.CREATED;
    private volatile String assignedRestaurant = null;
    private volatile double totalCost = 0.0;

    public Order(String user, Map<String, Integer> items) {
        this.id = ++counter;
        this.user = user;
        this.items = new HashMap<>();
        for (Map.Entry<String,Integer> e : items.entrySet()) {
            this.items.put(e.getKey().toLowerCase(), e.getValue());
        }
    }

    public long getId() { return id; }
    public String getUser() { return user; }
    public Map<String,Integer> getItems() { return Collections.unmodifiableMap(items); }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getAssignedRestaurant() { return assignedRestaurant; }
    public void setAssignedRestaurant(String r) { this.assignedRestaurant = r; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double c) { this.totalCost = c; }

    @Override
    public String toString() {
        return "Order{" + id + ", user='" + user + '\'' + ", items=" + items + ", status=" + status + ", assigned=" + assignedRestaurant + ", total=" + totalCost + '}';
    }
}

// File: repository/RestaurantRepository.java
package foodordersystem.repository;

import foodordersystem.model.Restaurant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RestaurantRepository {
    private final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();

    public void save(Restaurant r) {
        restaurants.put(r.getName().toLowerCase(), r);
    }

    public Optional<Restaurant> findByName(String name) {
        return Optional.ofNullable(restaurants.get(name.toLowerCase()));
    }

    public Collection<Restaurant> findAll() { return restaurants.values(); }
}

// File: repository/OrderRepository.java
package foodordersystem.repository;

import foodordersystem.model.Order;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository {
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();

    public void save(Order o) { orders.put(o.getId(), o); }
    public Optional<Order> findById(long id) { return Optional.ofNullable(orders.get(id)); }
    public Collection<Order> findAll() { return orders.values(); }
}

// File: strategy/SelectionStrategy.java
package foodordersystem.strategy;

import foodordersystem.model.Order;
import foodordersystem.model.Restaurant;
import java.util.*;

public interface SelectionStrategy {
    Optional<Restaurant> selectRestaurant(Order order, Collection<Restaurant> candidates);
}

// File: strategy/LowestCostStrategy.java
package foodordersystem.strategy;

import foodordersystem.model.Order;
import foodordersystem.model.Restaurant;
import java.util.*;

public class LowestCostStrategy implements SelectionStrategy {
    @Override
    public Optional<Restaurant> selectRestaurant(Order order, Collection<Restaurant> candidates) {
        double min = Double.MAX_VALUE;
        Restaurant chosen = null;
        for (Restaurant r : candidates) {
            double cost = r.computeCost(order.getItems());
            if (cost < min) {
                min = cost;
                chosen = r;
            } else if (cost == min) {
                // tie-breaker: higher rating
                if (chosen != null && r.getRating() > chosen.getRating()) chosen = r;
            }
        }
        return Optional.ofNullable(chosen);
    }
}

// File: strategy/HighestRatingStrategy.java
package foodordersystem.strategy;

import foodordersystem.model.Order;
import foodordersystem.model.Restaurant;
import java.util.*;

public class HighestRatingStrategy implements SelectionStrategy {
    @Override
    public Optional<Restaurant> selectRestaurant(Order order, Collection<Restaurant> candidates) {
        double best = -1.0;
        Restaurant chosen = null;
        for (Restaurant r : candidates) {
            if (r.getRating() > best) {
                best = r.getRating();
                chosen = r;
            } else if (r.getRating() == best) {
                // tie-breaker: lower cost
                if (chosen != null) {
                    double cost1 = chosen.computeCost(order.getItems());
                    double cost2 = r.computeCost(order.getItems());
                    if (cost2 < cost1) chosen = r;
                }
            }
        }
        return Optional.ofNullable(chosen);
    }
}

// File: service/RestaurantService.java
package foodordersystem.service;

import foodordersystem.model.Restaurant;
import foodordersystem.repository.RestaurantRepository;
import java.util.*;

public class RestaurantService {
    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) { this.repo = repo; }

    public void onboardRestaurant(Restaurant r) { repo.save(r); }
    public Optional<Restaurant> findByName(String name) { return repo.findByName(name); }
    public Collection<Restaurant> getAll() { return repo.findAll(); }
}

// File: exceptions/OrderException.java
package foodordersystem.exceptions;

public class OrderException extends RuntimeException {
    public OrderException(String msg) { super(msg); }
}

// File: service/OrderService.java
package foodordersystem.service;

import foodordersystem.model.*;
import foodordersystem.repository.*;
import foodordersystem.strategy.SelectionStrategy;
import foodordersystem.exceptions.OrderException;

import java.util.*;

public class OrderService {
    private final OrderRepository orderRepo;
    private final RestaurantRepository restaurantRepo;
    private SelectionStrategy strategy;

    public OrderService(OrderRepository orderRepo, RestaurantRepository restaurantRepo, SelectionStrategy strategy) {
        this.orderRepo = orderRepo;
        this.restaurantRepo = restaurantRepo;
        this.strategy = strategy;
    }

    public void setStrategy(SelectionStrategy s) { this.strategy = s; }

    public Order placeOrder(String user, Map<String,Integer> items) {
        Order order = new Order(user, items);
        orderRepo.save(order);

        // gather candidate restaurants that have all items and capacity
        List<Restaurant> candidates = new ArrayList<>();
        for (Restaurant r : restaurantRepo.findAll()) {
            if (r.hasAllItems(items) && r.getCurrentProcessing() < r.getMaxConcurrentOrders()) {
                candidates.add(r);
            }
        }

        if (candidates.isEmpty()) {
            order.setStatus(Order.Status.REJECTED);
            orderRepo.save(order);
            throw new OrderException("Order cannot be fulfilled by a single restaurant");
        }

        Optional<Restaurant> opt = strategy.selectRestaurant(order, candidates);
        if (!opt.isPresent()) {
            order.setStatus(Order.Status.REJECTED);
            orderRepo.save(order);
            throw new OrderException("No restaurant selected by strategy");
        }

        Restaurant chosen = opt.get();

        // Attempt to acquire slot in thread-safe manner
        boolean acquired = chosen.tryAcquireSlot();
        if (!acquired) {
            // someone else filled slots, re-evaluate: try other candidates
            candidates.remove(chosen);
            Optional<Restaurant> opt2 = strategy.selectRestaurant(order, candidates);
            if (!opt2.isPresent()) {
                order.setStatus(Order.Status.REJECTED);
                orderRepo.save(order);
                throw new OrderException("No restaurant available (capacity changed)");
            }
            chosen = opt2.get();
            if (!chosen.tryAcquireSlot()) {
                order.setStatus(Order.Status.REJECTED);
                orderRepo.save(order);
                throw new OrderException("No restaurant available (capacity raced)");
            }
        }

        // assign order
        order.setAssignedRestaurant(chosen.getName());
        double total = chosen.computeCost(items);
        order.setTotalCost(total);
        order.setStatus(Order.Status.ACCEPTED);
        orderRepo.save(order);

        return order;
    }

    public void markCompleted(long orderId, String restaurantName) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Order not found"));
        if (order.getStatus() != Order.Status.ACCEPTED) throw new OrderException("Only ACCEPTED orders can be completed");
        if (!order.getAssignedRestaurant().equalsIgnoreCase(restaurantName)) throw new OrderException("This restaurant is not assigned to the order");

        Restaurant r = restaurantRepo.findByName(restaurantName).orElseThrow(() -> new OrderException("Restaurant not found"));

        // complete
        order.setStatus(Order.Status.COMPLETED);
        r.releaseSlot();
        orderRepo.save(order);
    }

    public Optional<Order> findById(long id) { return orderRepo.findById(id); }
}

// File: demo/Main.java
package foodordersystem.demo;

import foodordersystem.model.*;
import foodordersystem.repository.*;
import foodordersystem.service.*;
import foodordersystem.strategy.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        RestaurantRepository rRepo = new RestaurantRepository();
        OrderRepository oRepo = new OrderRepository();
        RestaurantService rService = new RestaurantService(rRepo);

        // Onboard restaurants
        Restaurant r1 = new Restaurant("R1", 5, 4.5);
        r1.addMenuItem("Veg Biryani", 100);
        r1.addMenuItem("Paneer Butter Masala", 150);
        rService.onboardRestaurant(r1);

        Restaurant r2 = new Restaurant("R2", 5, 4.0);
        r2.addMenuItem("Paneer Butter Masala", 175);
        r2.addMenuItem("Idli", 10);
        r2.addMenuItem("Dosa", 50);
        r2.addMenuItem("Veg Biryani", 80);
        rService.onboardRestaurant(r2);

        Restaurant r3 = new Restaurant("R3", 1, 4.9);
        r3.addMenuItem("Gobi Manchurian", 150);
        r3.addMenuItem("Idli", 15);
        r3.addMenuItem("Paneer Butter Masala", 175);
        r3.addMenuItem("Dosa", 30);
        rService.onboardRestaurant(r3);

        // Update menus
        r1.addMenuItem("Chicken65", 250); // add
        r2.updateMenuItemPrice("Paneer Butter Masala", 150); // update

        // Setup order service with LowestCost
        OrderService oService = new OrderService(oRepo, rRepo, new LowestCostStrategy());

        // Order1: Ashwin {3Idli,1Dosa} Lowest cost -> expected R3
        Map<String,Integer> items1 = new HashMap<>(); items1.put("Idli", 3); items1.put("Dosa", 1);
        try {
            Order o1 = oService.placeOrder("Ashwin", items1);
            System.out.println("Order1 placed: " + o1);
        } catch (Exception e) { System.out.println("Order1 failed: " + e.getMessage()); }

        // Order2: Harish same items -> expected R2 because R3 capacity=1
        try {
            Order o2 = oService.placeOrder("Harish", items1);
            System.out.println("Order2 placed: " + o2);
        } catch (Exception e) { System.out.println("Order2 failed: " + e.getMessage()); }

        // Order3: Shruthi {3 Veg Biryani} selection Highest rating -> expected R1
        oService.setStrategy(new HighestRatingStrategy());
        Map<String,Integer> items3 = new HashMap<>(); items3.put("Veg Biryani", 3);
        try {
            Order o3 = oService.placeOrder("Shruthi", items3);
            System.out.println("Order3 placed: " + o3);
        } catch (Exception e) { System.out.println("Order3 failed: " + e.getMessage()); }

        // R3 marks Order1 as COMPLETED
        try {
            Optional<Order> found = oService.findById(1);
            if (found.isPresent()) {
                oService.markCompleted(1, "R3");
                System.out.println("Order1 completed by R3");
            }
        } catch (Exception e) { System.out.println("Complete failed: " + e.getMessage()); }

        // Order4: Harish same items, Lowest cost -> should go to R3 now
        oService.setStrategy(new LowestCostStrategy());
        try {
            Order o4 = oService.placeOrder("Harish", items1);
            System.out.println("Order4 placed: " + o4);
        } catch (Exception e) { System.out.println("Order4 failed: " + e.getMessage()); }

        // Order5: xyz asks 1Paneer Tikka + 1Idli -> cannot be fulfilled
        Map<String,Integer> items5 = new HashMap<>(); items5.put("Paneer Tikka", 1); items5.put("Idli",1);
        try {
            Order o5 = oService.placeOrder("xyz", items5);
            System.out.println("Order5 placed: " + o5);
        } catch (Exception e) { System.out.println("Order5 failed: " + e.getMessage()); }
    }
}
