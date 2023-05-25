package com.example.myapp_2.Data.Discount_Get_table;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myapp_2.Data.register.LoginFragment;
import com.example.myapp_2.R;

import com.example.myapp_2.RestaurantsPresenter;
import com.example.myapp_2.UI.view.activities.MainActivity;
import com.example.myapp_2.UI.view.fragments.RestaurantFragment;
import com.example.myapp_2.databinding.FragmentRestaurantsBinding;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment implements RecyclerViewInterface{
    RestaurantsPresenter restaurantsPresenter;

    public RestaurantsFragment(FragmentActivity a){
//        FragmentActivity a = getActivity();
//        FragmentManager m = a.getSupportFragmentManager();
        restaurantsPresenter = new RestaurantsPresenter(this, a.getSupportFragmentManager());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        FragmentRestaurantsBinding binding = FragmentRestaurantsBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(170);
        view.startAnimation(anim);

        binding.recyclerView.setAdapter(restaurantsPresenter.getRestaurantsRecyclerViewAdapterAdapter());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).startRegistration(); // вызов метода startRegistration()
                Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.main_activity_fragment_container);
                requireActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        });

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_container, new LoginFragment());
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.addToBackStack(null);
                transaction.commit();
                getActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.GONE);
                Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.main_activity_fragment_container);
                requireActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        });

        return binding.getRoot();
    }

    public void onItemClick(int position) {
        restaurantsPresenter.onItemClicked(position);
    }
}