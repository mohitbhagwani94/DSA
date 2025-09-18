package FoodOrderingSystem.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {
    private final String name;
    private final Map<String, MenuItem> menu = new HashMap<>();
    private final double rating;
    private final int maxConcurrentOrders;
    private final AtomicInteger currentProcessingOrders = new AtomicInteger(0);

    public Restaurant(String name, Collection<MenuItem> initialMenu, double rating, int maxConcurrentOrders) {
        this.name = Objects.requireNonNull(name).trim();
        if (rating < 0 || rating > 5) throw new IllegalArgumentException("Rating must be 0-5");
        if (maxConcurrentOrders < 1) throw new IllegalArgumentException("maxConcurrentOrders must be >= 1");
        this.rating = rating;
        this.maxConcurrentOrders = maxConcurrentOrders;
        if (initialMenu != null) {
            for (MenuItem mi : initialMenu) {
                menu.put(mi.getName().toLowerCase(), mi);
            }
        }
    }

    public String getName() { return name; }
    public double getRating() { return rating; }
    public int getMaxConcurrentOrders() { return maxConcurrentOrders; }
    public int getCurrentProcessingOrders() { return currentProcessingOrders.get(); }

    public synchronized boolean tryAcquireSlot() {
        while (true) {
            int curr = currentProcessingOrders.get();
            if (curr >= maxConcurrentOrders) return false;
            if (currentProcessingOrders.compareAndSet(curr, curr + 1)) return true;
        }
    }

    public void releaseSlot() {
        int newVal = currentProcessingOrders.decrementAndGet();
        if (newVal < 0) {
            currentProcessingOrders.set(0);
            throw new IllegalStateException("Processing orders count went negative for restaurant " + name);
        }
    }

    public synchronized void addMenuItem(MenuItem item) {
        menu.put(item.getName().toLowerCase(), item);
    }

    public synchronized void updateMenuItemPrice(String itemName, double price) {
        MenuItem mi = menu.get(itemName.toLowerCase());
        if (mi == null) throw new IllegalArgumentException("Menu item not present: " + itemName);
        mi.setPrice(price);
    }

    public Optional<MenuItem> getMenuItem(String itemName) {
        return Optional.ofNullable(menu.get(itemName.toLowerCase()));
    }

    public boolean canFulfill(Map<String, Integer> items) {
        for (String name : items.keySet()) {
            if (!menu.containsKey(name.toLowerCase())) return false;
        }
        return true;
    }

    public double computeCost(Map<String, Integer> items) {
        double sum = 0.0;
        for (Map.Entry<String, Integer> e : items.entrySet()) {
            MenuItem mi = menu.get(e.getKey().toLowerCase());
            if (mi == null) throw new IllegalArgumentException("Restaurant can't fulfill item: " + e.getKey());
            sum += mi.getPrice() * e.getValue();
        }
        return sum;
    }

    public synchronized List<MenuItem> listMenu() {
        return new ArrayList<>(menu.values());
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", maxConcurrentOrders=" + maxConcurrentOrders +
                ", currentProcessing=" + currentProcessingOrders.get() +
                ", menu=" + menu.values() +
                '}';
    }
}
