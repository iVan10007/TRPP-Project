package com.example.myapp_2.Data.Discount_Get_table;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.Data.List_1.Product;
import com.example.myapp_2.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductAdapter1 extends RecyclerView.Adapter<ProductAdapter1.ProductViewHolder> {
    private List<Product> products;
    private Context context;

    public ProductAdapter1(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product3, parent, false);
        ProductViewHolder holder = new ProductViewHolder(view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productImage.setImageResource(product.getImageResource());
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.oldPriceTextView.setText(context.getString(R.string.old_price, String.format("%.2f", product.getPrice())));
        holder.newPriceTextView.setText(context.getString(R.string.price, String.format("%.2f", product.getNewPrice())));
        holder.oldPriceTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product("Product 1", "Description 1", R.drawable.food_1, 0.0f);
        products.add(product1);

        Product product2 = new Product("Product 2", "Description 2", R.drawable.food_2, 0.0f);
        products.add(product2);

        Product product3 = new Product("Product 3", "Description 3", R.drawable.food_1_3, 0.0f);
        products.add(product3);

        Product product4 = new Product("Product 4", "Description 4", R.drawable.food_1_2, 0.0f);
        products.add(product4);

        Product product5 = new Product("Product 5", "Description 5", R.drawable.food_3_4, 0.0f);
        products.add(product5);

        return products;
    }

    public void sortProductsByPrice() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public Button goToRestaurantButton;
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView oldPriceTextView;
        TextView newPriceTextView;

        public ProductViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.product_image);
            productName = view.findViewById(R.id.product_name);
            productDescription = view.findViewById(R.id.product_description);
            goToRestaurantButton = itemView.findViewById(R.id.goRestaurantButton);
            oldPriceTextView = itemView.findViewById(R.id.old_price);
            newPriceTextView = itemView.findViewById(R.id.new_price);
        }
    }
}