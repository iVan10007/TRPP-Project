package com.example.myapp_2;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapp_2.Data.Discount_Get_table.RecyclerViewInterface;
import com.example.myapp_2.Data.Discount_Get_table.RestaurantModel;
import com.example.myapp_2.Data.Discount_Get_table.RestaurantsFragment;
import com.example.myapp_2.Data.Discount_Get_table.RestaurantsRecyclerViewAdapter;
import com.example.myapp_2.UI.view.fragments.RestaurantFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RestaurantsPresenter{
    RestaurantsRecyclerViewAdapter restaurantsRecyclerViewAdapter;
    RestaurantsFragment restaurantsFragment;
    Repository repository;

    FragmentManager fragmentManager;

    Map <Integer, Integer> restorantIds = new HashMap<Integer, Integer>();

    public RestaurantsPresenter(RestaurantsFragment restaurantsFragment, FragmentManager fragmentManager) {
        this.restaurantsFragment = restaurantsFragment;
        this.repository = new Repository();
        this.fragmentManager = fragmentManager;
        restaurantsRecyclerViewAdapter = new RestaurantsRecyclerViewAdapter(restaurantsFragment.getContext(), setupRestaurants(), restaurantsFragment);

    }
    public ArrayList<RestaurantModel> setupRestaurants(){
        ArrayList<RestaurantModel> restaurantModels = new ArrayList<>();
        Restaurants restaurants = repository.getRestaurantsDataSource();


        for(Restaurant r: restaurants.getRestaurants()){
            RestaurantModel m = new RestaurantModel(r.getId(), r.getName(), r.getAdress(), r.getWorkTime(), r.getKitchenType(),r.getRestaurantPicture());
            restaurantModels.add(m);
        }

        return  restaurantModels;
    }

    public RestaurantsRecyclerViewAdapter getRestaurantsRecyclerViewAdapterAdapter(){
        return restaurantsRecyclerViewAdapter;
    }

    public void onItemClicked(int position) { // надо чуток переделать
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int id = position;
        transaction.replace(R.id.main_activity_fragment_container, new RestaurantFragment(id)).addToBackStack(null).commit();
    }
}
