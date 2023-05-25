package com.example.myapp_2;

import android.widget.TextView;

import com.example.myapp_2.Data.Discount_Get_table.RestaurantsFragment;
import com.example.myapp_2.Data.Discount_Get_table.RestaurantsRecyclerViewAdapter;
import com.example.myapp_2.UI.view.adapters.SliderAdapter;
import com.example.myapp_2.UI.view.fragments.RestaurantFragment;

public class RestaurantPresenter{

    private int id;
    SliderAdapter sliderAdapter;
    RestaurantFragment restaurantFragment;
    Repository repository;

    public RestaurantPresenter(RestaurantFragment restaurantFragment, int id) {
        this.id = id;
        this.restaurantFragment = restaurantFragment;
        this.repository = new Repository();
        sliderAdapter = new SliderAdapter(restaurantFragment.getContext(), getSliderImages());
    }

    public int[] getSliderImages(){
        return repository.getResteurantById(id).getSliderRestaurantImages();
    }

    public SliderAdapter getSliderAdapter() {
        return sliderAdapter;
    }
}
