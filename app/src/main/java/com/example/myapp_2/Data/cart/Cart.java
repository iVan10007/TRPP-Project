package com.example.myapp_2.Data.cart;

import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.DataSource;

import java.util.ArrayList;
import java.util.List;

public class Cart extends DataSource {
    private static Cart instance;

    private List<CartItem> items;

    public Cart() {
        super("Cart");
        items = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(product, 1));
    }

    public void updateItem(CartItem item, int newQuantity) {
        if (newQuantity <= 0) {
            removeItem(item);
        } else {
            item.setQuantity(newQuantity);
        }
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
}
