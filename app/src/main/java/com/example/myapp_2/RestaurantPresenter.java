package com.example.myapp_2;

import com.example.myapp_2.Data.Discount_Get_table.RestaurantsFragment;
import com.example.myapp_2.Data.Discount_Get_table.RestaurantsRecyclerViewAdapter;
import com.example.myapp_2.UI.view.adapters.SliderAdapter;
import com.example.myapp_2.UI.view.fragments.RestaurantFragment;

public class RestaurantPresenter{

    RestaurantsRecyclerViewAdapter restaurantsRecyclerViewAdapter;

    SliderAdapter sliderAdapter;

    RestaurantFragment restaurantFragment;
    Repository repository;

    public RestaurantPresenter(RestaurantFragment restaurantFragment) {
        this.restaurantFragment = restaurantFragment;
        this.repository = new Repository();
        sliderAdapter = new SliderAdapter(restaurantFragment.getContext(), getSliderImages());
    }

    public int[] getSliderimages(){
        return
    }

    public SliderAdapter getSliderAdapter() {
        return sliderAdapter;
    }
}
