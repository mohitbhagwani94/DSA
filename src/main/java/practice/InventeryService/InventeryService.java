package practice.InventeryService;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

interface IProduct {
    void setName(String name);

    String getName();

    void setCategory(String category);

    String getCategory();

    void setStock(int stock);

    int getStock();

    void setPrice(int price);

    int getPrice();
}

interface IInventory {
    void addProduct(IProduct product);

    void removeProduct(IProduct product);

    long calculateTotalValue();

    List<IProduct> getProductsByCategory(String category);

    List<IProduct> searchProductsByName(String name);

    Map<String, Integer> getProductsByCategoryWithCount();

    Map<String, List<IProduct>> getAllProductsByCategory();
}


class Product implements IProduct {
    private String name;
    private String category;
    private int stock;
    private int price;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}

class Inventory implements IInventory {
    private List<IProduct> products = new ArrayList<>();

    @Override
    public void addProduct(IProduct product) {
        products.add(product);
    }

    @Override
    public void removeProduct(IProduct product) {
        products.remove(product);
    }

    @Override
    public long calculateTotalValue() {
        return products.stream()
                .mapToLong(p -> (long) p.getStock() * p.getPrice())
                .sum();
    }

    @Override
    public List<IProduct> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparing(IProduct::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<IProduct> searchProductsByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .sorted(Comparator.comparing(IProduct::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> getProductsByCategoryWithCount() {
        Map<String, Integer> map = new TreeMap<>();
        for (IProduct p : products) {
            map.put(p.getCategory(), map.getOrDefault(p.getCategory(), 0) + 1);
        }
        return map;
    }

    @Override
    public Map<String, List<IProduct>> getAllProductsByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        IProduct::getCategory,
                        TreeMap :: new,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(IProduct::getName))
                                        .collect(Collectors.toList())
                        )
                ));
    }
}
