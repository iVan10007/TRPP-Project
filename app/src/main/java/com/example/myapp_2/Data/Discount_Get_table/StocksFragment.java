package com.example.myapp_2.Data.Discount_Get_table;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.R;

import java.util.ArrayList;
import java.util.List;

public class StocksFragment extends Fragment {

    ViewPager viewPager;
    StocksAdapter adapter;
    ArrayList<StocksModel> stocksModelArrayList;
    ProductAdapter1 productAdapter;
    List<Product> products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(200);
        view.startAnimation(anim);
         RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        stocksModelArrayList = new ArrayList<>();
        stocksModelArrayList.add(new StocksModel("Title1", "Description1", R.drawable.food_3));
        stocksModelArrayList.add(new StocksModel("Title2", "Description2", R.drawable.food_4));
        stocksModelArrayList.add(new StocksModel("Title2", "Description2", R.drawable.food_1_2));

        viewPager = view.findViewById(R.id.viewPager);
        adapter = new StocksAdapter(getContext(), stocksModelArrayList);
        viewPager.setAdapter(adapter);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int totalItems = viewPager.getAdapter().getCount();
                int nextItem = currentItem == totalItems - 1 ? 0 : currentItem + 1;
                viewPager.setCurrentItem(nextItem);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);

        products = getProducts();

        products.get(0).setPrice(300);
        products.get(1).setPrice(120);
        products.get(2).setPrice(140);
        products.get(3).setPrice(420);
        products.get(4).setPrice(380);
        products.get(5).setPrice(105);
        products.get(6).setPrice(200);
        products.get(7).setPrice(150);
        products.get(8).setPrice(190);
        products.get(9).setPrice(299.99);

        products.get(0).setNewPrice(250);
        products.get(1).setNewPrice(100);
        products.get(2).setNewPrice(130);
        products.get(3).setNewPrice(370);
        products.get(4).setNewPrice(70);
        products.get(5).setNewPrice(90);
        products.get(6).setNewPrice(150);
        products.get(7).setNewPrice(135);
        products.get(8).setNewPrice(160);
        products.get(9).setNewPrice(249.99);


        productAdapter = new ProductAdapter1(getContext(), products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(productAdapter);

        return view;
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product("Product 1", "Description 1", R.drawable.food_3_1jpg, 0.0f, 0.0f, 0.0f);
        products.add(product1);

        Product product2 = new Product("Product 2", "Description 2", R.drawable.food_1_1, 0.0f, 0.0f, 0.0f);
        products.add(product2);

        Product product3 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f, 0.0f, 0.0f);
        products.add(product3);

        Product product4 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f, 0.0f, 0.0f);
        products.add(product3);

        Product product5 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f, 0.0f, 0.0f);
        products.add(product3);

        Product product6 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f, 0.0f, 0.0f);
        products.add(product3);

        Product product7 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f, 0.0f, 0.0f);
        products.add(product3);

        Product product8 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f, 0.0f, 0.0f);
        products.add(product3);

        Product product9 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f, 0.0f, 0.0f);
        products.add(product3);

        Product product10 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f, 0.0f, 0.0f);
        products.add(product3);

        return products;
    }
}
