package FoodOrderingSystem.model;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final String id;
    private final String user;
    private final Map<String, Integer> items;
    private final String selectionCriteria;
    private volatile OrderStatus status;
    private volatile String assignedRestaurant;
    private volatile Double assignedCost;
    private final Instant createdAt;

    public Order(String user, Map<String, Integer> items, String selectionCriteria) {
        this.id = UUID.randomUUID().toString();
        this.user = Objects.requireNonNull(user).trim();
        this.items = Objects.requireNonNull(items);
        this.selectionCriteria = selectionCriteria;
        this.status = OrderStatus.CREATED;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }
    public String getUser() { return user; }
    public Map<String, Integer> getItems() { return items; }
    public String getSelectionCriteria() { return selectionCriteria; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public String getAssignedRestaurant() { return assignedRestaurant; }
    public void setAssignedRestaurant(String assignedRestaurant) { this.assignedRestaurant = assignedRestaurant; }
    public Double getAssignedCost() { return assignedCost; }
    public void setAssignedCost(Double cost) { this.assignedCost = cost; }
    public Instant getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", items=" + items +
                ", selectionCriteria='" + selectionCriteria + '\'' +
                ", status=" + status +
                ", assignedRestaurant='" + assignedRestaurant + '\'' +
                ", assignedCost=" + assignedCost +
                ", createdAt=" + createdAt +
                '}';
    }
}
