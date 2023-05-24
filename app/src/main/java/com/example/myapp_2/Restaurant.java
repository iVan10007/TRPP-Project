package com.example.myapp_2;

import com.example.myapp_2.Data.Discount_Get_table.RestaurantsFragment;
import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.Data.cart.Cart;

import java.util.List;

public class Restaurant extends DataSource{
    private String name, kitchenType, adress, workTime;
    List<Product> productList;
    int[] sliderRestaurantImages;
    Cart cart; // корзина


    public Restaurant() {
        super("Restaurant");
    }

}
