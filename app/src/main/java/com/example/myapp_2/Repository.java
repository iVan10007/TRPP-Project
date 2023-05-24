package com.example.myapp_2;

import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.Data.cart.Cart;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    Restaurants restaurants;

    public Repository() {

        restaurants = new Restaurants();

        Product product1 = new Product("Окрошка","300г Колбаса, картофель отварной, яйцо, зелень, квас",R.drawable.food_1,350);
        Product product2 = new Product("Пельмени сибирские", "250/10г Тесто, фарш, лук репчатый, сметана",R.drawable.food_1_1,420);
        Product product3 = new Product("Вареники с вишней", "270г Тесто, вишня без косточки, сахар", R.drawable.food_1_2,240);
        Product product4 = new Product("Холодец с хреном", "180г Мясо, чеснок, желатин, морковь", R.drawable.food_1_3, 310);
        Product product5 = new Product("Борщ со сметаной", "320г Бульон, свекла, морковь, лук",R.drawable.food_3_1jpg, 228);

        ArrayList<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        productList1.add(product3);
        productList1.add(product4);
        productList1.add(product5);

        int[] sliderImages = {R.drawable.first_1, R.drawable.first_2, R.drawable.first_3, R.drawable.first_4, R.drawable.first_5};


        Restaurant restaurant1 = new Restaurant(1,"Русский дом", "Русская кухня", "Москва, Рочдельская ул., 11/5", "9:00 - 23:00", R.drawable.first_1, productList1, sliderImages, new Cart());
        restaurants.add(restaurant1);
        Restaurant restaurant2 = new Restaurant(2,"Русский дом", "Русская кухня", "Москва, Рочдельская ул., 11/5", "9:00 - 23:00", R.drawable.first_2, productList1, sliderImages, new Cart());
        restaurants.add(restaurant2);
    }

    public Restaurants getRestaurantsDataSource() {
        return restaurants;
    }

    public Restaurant getResteurantById(int id){
        for(Restaurant r: restaurants.getRestaurants())
        {
            if(r.getId() == id)
                return r;
        }
    }
}
