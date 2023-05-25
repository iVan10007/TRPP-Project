package com.example.myapp_2.Data.register;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapp_2.Data.Discount_Get_table.RestaurantsFragment;
import com.example.myapp_2.R;
import com.example.myapp_2.UI.view.activities.MainActivity;

import java.util.List;

public class RegistrationFragment extends Fragment {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonRegister;

    private UserDAO userDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userDAO = new UserDAO(getActivity());
        userDAO.open();
        // сохраняем фрагмент в памяти во время анимации перехода

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(200);
        view.startAnimation(anim);

        getActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.GONE);
        editTextName = view.findViewById(R.id.editTextName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonRegister = view.findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                long rowID = userDAO.register(name, email, password);

                if (rowID > 0) {
                    Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();

// set profile_num to the newly registered user’s id
                    LoginFragment.profile_num = userDAO.getUserByEmail(email).getId();

                    // go to another fragment
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("MY_PREFS_NAME", getActivity().MODE_PRIVATE).edit();
                    editor.putInt("profile_num", LoginFragment.profile_num );
                    editor.apply();

                } else if (rowID == -1) {
                    Toast.makeText(getActivity(), "Invalid email", Toast.LENGTH_SHORT).show();
// print all users to the log
                    List<User> users = userDAO.getAllUsers();
                    Log.d("UserDatabase", "All users:");
                    for (User user : users) {
                        Log.d("UserDatabase", user.toString());
                    }

                } else {
                    Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonExit = view.findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_fragment_container, new RestaurantsFragment(requireActivity()));
              //  transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                transaction.addToBackStack(null);
                transaction.commit();
                getActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.VISIBLE);
                Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.main_activity_container);
                requireActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        });


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }
}
