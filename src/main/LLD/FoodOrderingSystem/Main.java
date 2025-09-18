package FoodOrderingSystem;

import FoodOrderingSystem.strategy.LowestCostStrategy;
import FoodOrderingSystem.strategy.SelectionStrategy;
import FoodOrderingSystem.model.MenuItem;
import FoodOrderingSystem.model.Order;
import FoodOrderingSystem.model.OrderStatus;
import FoodOrderingSystem.repository.InMemoryOrderRepository;
import FoodOrderingSystem.repository.InMemoryRestaurantRepository;
import FoodOrderingSystem.service.OrderService;
import FoodOrderingSystem.service.RestaurantService;
import FoodOrderingSystem.strategy.HighestRatingStrategy;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        InMemoryRestaurantRepository restRepo = new InMemoryRestaurantRepository();
        InMemoryOrderRepository orderRepo = new InMemoryOrderRepository();
        RestaurantService restaurantService = new RestaurantService(restRepo);
        OrderService orderService = new OrderService(orderRepo, restRepo);

        restaurantService.onboardRestaurant("R1",
                Arrays.asList(new MenuItem("Veg Biryani", 100), new MenuItem("Paneer Butter Masala", 150)),
                4.5, 5);

        restaurantService.onboardRestaurant("R2",
                Arrays.asList(new MenuItem("Paneer Butter Masala", 175), new MenuItem("Idli", 10),
                        new MenuItem("Dosa", 50), new MenuItem("Veg Biryani", 80)),
                4.0, 5);

        restaurantService.onboardRestaurant("R3",
                Arrays.asList(new MenuItem("Gobi Manchurian", 150), new MenuItem("Idli", 15),
                        new MenuItem("Paneer Butter Masala", 175), new MenuItem("Dosa", 30)),
                4.9, 1);

        System.out.println("Adding Chicken65 to R1");
        restaurantService.addMenuItem("R1", new MenuItem("Chicken65", 250));

        System.out.println("Updating R2 Paneer Butter Masala price to 150");
        restaurantService.updateMenuItemPrice("R2", "Paneer Butter Masala", 150);

        SelectionStrategy lowestCost = new LowestCostStrategy();
        SelectionStrategy highestRating = new HighestRatingStrategy();

        Map<String, Integer> o1Items = new HashMap<>();
        o1Items.put("Idli", 3);
        o1Items.put("Dosa", 1);
        Order order1 = orderService.placeOrder("Ashwin", o1Items, lowestCost);
        System.out.println("Order1 assigned to: " + order1.getAssignedRestaurant() + " (status=" + order1.getStatus() + ")");

        Order order2 = orderService.placeOrder("Harish", o1Items, lowestCost);
        System.out.println("Order2 assigned to: " + order2.getAssignedRestaurant() + " (status=" + order2.getStatus() + ")");

        Map<String, Integer> o3Items = new HashMap<>();
        o3Items.put("Veg Biryani", 3);
        Order order3 = orderService.placeOrder("Shruthi", o3Items, highestRating);
        System.out.println("Order3 assigned to: " + order3.getAssignedRestaurant() + " (status=" + order3.getStatus() + ")");

        if (order1.getAssignedRestaurant() != null) {
            System.out.println("R3 marking Order1 as COMPLETED");
            orderService.markOrderCompleted("R3", order1.getId());
            System.out.println("Order1 status now: " + orderService.findById(order1.getId()).get().getStatus());
        }

        Order order4 = orderService.placeOrder("Harish", o1Items, lowestCost);
        System.out.println("Order4 assigned to: " + order4.getAssignedRestaurant() + " (status=" + order4.getStatus() + ")");

        Map<String, Integer> o5Items = new HashMap<>();
        o5Items.put("Paneer Tikka", 1);
        o5Items.put("Idli", 1);
        Order order5 = orderService.placeOrder("xyz", o5Items, lowestCost);
        System.out.println("Order5 status: " + order5.getStatus() + " assigned: " + order5.getAssignedRestaurant());

        System.out.println("Final Restaurants:");
        for (var r : restaurantService.listAll()) {
            System.out.println(r);
        }

        assert order1.getAssignedRestaurant() != null && order1.getAssignedRestaurant().equalsIgnoreCase("R3");
        assert order2.getAssignedRestaurant() != null && order2.getAssignedRestaurant().equalsIgnoreCase("R2");
        assert order3.getAssignedRestaurant() != null && order3.getAssignedRestaurant().equalsIgnoreCase("R1");
        assert order5.getStatus() == OrderStatus.REJECTED;

        System.out.println("Demo assertions passed.");
    }
}
