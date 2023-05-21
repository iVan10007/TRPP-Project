package com.example.myapp_2.Data.Discount_Get_table;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product("Product 1", "Description 1", R.drawable.food_3_1jpg, 0.0f);
        products.add(product1);

        Product product2 = new Product("Product 2", "Description 2", R.drawable.food_1_1, 0.0f);
        products.add(product2);

        Product product3 = new Product("Product 3", "Description 3", R.drawable.food_1_4, 0.0f);
        products.add(product3);

        Product product4 = new Product("Product 4", "Description 4", R.drawable.food_1_1, 0.0f);
        products.add(product4);

        Product product5 = new Product("Product 5", "Description 5", R.drawable.food_1_1, 0.0f);
        products.add(product5);

        Product product6 = new Product("Product 6", "Description 6", R.drawable.food_1_1,0.0f);
        products.add(product6);

        Product product7 = new Product("Product 7", "Description 7", R.drawable.food_3_2,0.0f);
        products.add(product7);

        Product product8 = new Product("Product 8", "Description 8", R.drawable.food_3_3,0.0f);
        products.add(product8);

        Product product9 = new Product("Product 9", "Description 9", R.drawable.food_1_1,0.0f);
        products.add(product9);

        Product product10 = new Product("Product 10", "Description 10", R.drawable.food_1_1,0.0f);
        products.add(product10);

        return products;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);
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
        productAdapter = new ProductAdapter1(getContext(), products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(productAdapter);

        return view;
    }
}
