package com.example.myapp_2.Data.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp_2.R;

public class CartFragment extends Fragment {

    private TextView totalPriceTextView;
    private Button placeOrderButton;
    private RecyclerView recyclerView;

    private CartAdapter adapter;
    private Cart cart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

// Инициализируем UI и получаем объект Cart из синглтона
        totalPriceTextView = view.findViewById(R.id.total_price_text_view);
        placeOrderButton = view.findViewById(R.id.place_order_button);
        recyclerView = view.findViewById(R.id.cart_recycler_view);

        cart = Cart.getInstance();

        adapter = new CartAdapter(cart.getItems(), new OnCartItemListener() {
            @Override
            public void onQuantityChanged(CartItem item, int newQuantity) {
                cart.updateItem(item, newQuantity);
                updateTotalPrice();
            }

            @Override
            public void onRemoveButtonClick(CartItem item) {
                cart.removeItem(item);
                adapter.setItems(cart.getItems());
                updateTotalPrice();
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateTotalPrice();

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Опять работа???",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void placeOrder() {
// Здесь должен быть код для оформления заказа
    }

    private void updateTotalPrice() {
        double totalPrice = cart.getTotalPrice();
        totalPriceTextView.setText(getString(R.string.cart_item_total_price, totalPrice));
    }
}