package com.example.myapp_2;

import java.util.ArrayList;

public class Restaurants extends DataSource{
    ArrayList<Restaurant> restaurants;

    public Restaurants() {

        super("Restaurants");
        restaurants = new ArrayList<>();
    }

    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
    }

    public void add(Restaurant restaurant) {
        restaurants.add(restaurant);
    }
}
