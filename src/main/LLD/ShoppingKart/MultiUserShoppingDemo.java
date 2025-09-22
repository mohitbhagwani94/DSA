package ShoppingKart;

import java.util.*;

// -------------------- Product --------------------
class Product {
    private final String productId;
    private final String name;
    private int stock;   // mutable (inventory changes)

    public Product(String productId, String name, int stock) {
        this.productId = productId;
        this.name = name;
        this.stock = stock;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public int getStock() { return stock; }

    public boolean isInStock(int quantity) {
        return stock >= quantity;
    }

    public void reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            throw new IllegalArgumentException("Not enough stock available.");
        }
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }
}

// -------------------- Product Catalog --------------------
class ProductCatalog {
    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public boolean productExists(String productId) {
        return products.containsKey(productId);
    }
}

// -------------------- Cart Item --------------------
class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}

// -------------------- Cart --------------------
class Cart {
    private final Map<String, CartItem> items = new HashMap<>();
    private static final int MAX_CART_SIZE = 10;   // limit cart items

    public void addItem(Product product, int quantity) {
        if (!product.isInStock(quantity)) {
            throw new IllegalArgumentException("Product out of stock: " + product.getName());
        }
        if (items.size() >= MAX_CART_SIZE && !items.containsKey(product.getProductId())) {
            throw new IllegalStateException("Cart item limit exceeded.");
        }

        CartItem item = items.get(product.getProductId());
        if (item == null) {
            items.put(product.getProductId(), new CartItem(product, quantity));
        } else {
            item.addQuantity(quantity);
        }
        product.reduceStock(quantity);
    }

    public void removeItem(String productId) {
        CartItem removed = items.remove(productId);
        if (removed != null) {
            removed.getProduct().increaseStock(removed.getQuantity());
        }
    }

    public List<CartItem> viewCart() {
        return new ArrayList<>(items.values());
    }

    public void checkout() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot checkout with an empty cart.");
        }
        System.out.println("Checkout successful. Items purchased:");
        for (CartItem item : items.values()) {
            System.out.println("- " + item.getProduct().getName() + " x " + item.getQuantity());
        }
        items.clear();
    }
}

// -------------------- User --------------------
class User {
    private final String userId;
    private final String name;
    private final Cart cart;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.cart = new Cart();
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public Cart getCart() { return cart; }
}

// -------------------- ShoppingCartSystem (Manager) --------------------
class ShoppingCartSystem {
    private final ProductCatalog catalog;
    private final Map<String, User> users = new HashMap<>();

    public ShoppingCartSystem(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public void registerUser(String userId, String name) {
        if (users.containsKey(userId)) {
            throw new IllegalArgumentException("User already exists: " + userId);
        }
        users.put(userId, new User(userId, name));
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    // convenience methods
    public void addItem(String userId, String productId, int quantity) {
        User user = getUser(userId);
        if (user == null) throw new IllegalArgumentException("User not found.");
        Product product = catalog.getProduct(productId);
        if (product == null) throw new IllegalArgumentException("Product not found.");
        user.getCart().addItem(product, quantity);
    }

    public void removeItem(String userId, String productId) {
        User user = getUser(userId);
        if (user == null) throw new IllegalArgumentException("User not found.");
        user.getCart().removeItem(productId);
    }

    public void viewCart(String userId) {
        User user = getUser(userId);
        if (user == null) throw new IllegalArgumentException("User not found.");
        System.out.println("Cart for " + user.getName() + ":");
        for (CartItem item : user.getCart().viewCart()) {
            System.out.println("- " + item.getProduct().getName() + " x " + item.getQuantity());
        }
    }

    public void checkout(String userId) {
        User user = getUser(userId);
        if (user == null) throw new IllegalArgumentException("User not found.");
        System.out.println("User " + user.getName() + " checking out:");
        user.getCart().checkout();
    }
}

// -------------------- Demo --------------------
public class MultiUserShoppingDemo {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Product("P1", "Laptop", 5));
        catalog.addProduct(new Product("P2", "Phone", 3));
        catalog.addProduct(new Product("P3", "Headphones", 10));

        ShoppingCartSystem system = new ShoppingCartSystem(catalog);

        // Register users
        system.registerUser("U1", "Alice");
        system.registerUser("U2", "Bob");

        try {
            // Alice shopping
            system.addItem("U1", "P1", 1);
            system.addItem("U1", "P3", 2);
            system.viewCart("U1");

            // Bob shopping
            system.addItem("U2", "P2", 1);
            system.viewCart("U2");

            // Checkout
            system.checkout("U1");
            system.checkout("U2");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
