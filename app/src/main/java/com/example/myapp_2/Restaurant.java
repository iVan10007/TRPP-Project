package com.example.myapp_2;

import com.example.myapp_2.Data.Discount_Get_table.RestaurantsFragment;
import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.Data.cart.Cart;

import java.util.List;

public class Restaurant extends DataSource{

    int id;
    private String name, kitchenType, adress, workTime;
    private List<Product> productList;

    private int restaurantPicture;
    private int[] sliderRestaurantImages;
    private Cart cart; // корзина

    public Restaurant(int id, String name, String kitchenType, String adress, String workTime, int restaurantPicture , List<Product> productList, int[] sliderRestaurantImages, Cart cart) {
        super("Restaurant");
        this.id = id;
        this.name = name;
        this.kitchenType = kitchenType;
        this.adress = adress;
        this.workTime = workTime;
        this.productList = productList;
        this.sliderRestaurantImages = sliderRestaurantImages;
        this.cart = cart;
        this.restaurantPicture = restaurantPicture;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKitchenType() {
        return kitchenType;
    }

    public void setKitchenType(String kitchenType) {
        this.kitchenType = kitchenType;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public int getRestaurantPicture() {
        return restaurantPicture;
    }

    public void setRestaurantPicture(int restaurantPicture) {
        this.restaurantPicture = restaurantPicture;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int[] getSliderRestaurantImages() {
        return sliderRestaurantImages;
    }

    public void setSliderRestaurantImages(int[] sliderRestaurantImages) {
        this.sliderRestaurantImages = sliderRestaurantImages;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
