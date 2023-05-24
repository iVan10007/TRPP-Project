package com.example.myapp_2;



import com.example.myapp_2.Data.Discount_Get_table.RestaurantModel;
import com.example.myapp_2.Data.Discount_Get_table.RestaurantsFragment;
import com.example.myapp_2.Data.Discount_Get_table.RestaurantsRecyclerViewAdapter;

import java.util.ArrayList;

public class RestaurantsPresenter{
    RestaurantsRecyclerViewAdapter restaurantsRecyclerViewAdapter;

    RestaurantsFragment restaurantsFragment;
    Repository repository;

    public RestaurantsPresenter(RestaurantsFragment restaurantsFragment) {
        this.restaurantsFragment = restaurantsFragment;
        this.repository = new Repository();
        restaurantsRecyclerViewAdapter = new RestaurantsRecyclerViewAdapter(restaurantsFragment.getContext(), setupRestaurants(), restaurantsFragment);

    }
    public ArrayList<RestaurantModel> setupRestaurants(){
        ArrayList<RestaurantModel> restaurantModels = new ArrayList<>();
        Restaurants restaurants = repository.getRestaurantsDataSource();


        for(Restaurant r: restaurants.getRestaurants()){
            RestaurantModel m = new RestaurantModel(r.getName(), r.getAdress(), r.getWorkTime(), r.getKitchenType(),r.getRestaurantPicture());
            restaurantModels.add(m);
        }

        return  restaurantModels;
    }

    public RestaurantsRecyclerViewAdapter getRestaurantsRecyclerViewAdapterAdapter(){
        return restaurantsRecyclerViewAdapter;
    }

}
