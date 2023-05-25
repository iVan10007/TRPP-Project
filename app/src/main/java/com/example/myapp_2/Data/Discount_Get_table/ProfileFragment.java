package com.example.myapp_2.Data.Discount_Get_table;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapp_2.Data.register.User;
import com.example.myapp_2.Data.register.UserDAO;
import com.example.myapp_2.R;
import com.example.myapp_2.UI.view.fragments.RestaurantFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProfileFragment extends Fragment {

    private UserDAO userDAO;
    private int userId;
    private User user;

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonUpdate;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userDAO = new UserDAO(getActivity());
        userDAO.open();

// получение id последнего авторизовавшегося пользователя
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs_profile", Context.MODE_PRIVATE);
        List<User> users = userDAO.getAllUsers();
        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", getActivity().MODE_PRIVATE);
        int profile_num = prefs.getInt("profile_num", 0); // 0 - значение по умолчанию
        userId = sharedPreferences.getInt("lastLoggedInUserId", profile_num);

// получение данных пользователя по его id
        user = userDAO.getUserById(userId);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(200);
        view.startAnimation(anim);
        ImageButton btnFirstFragment = view.findViewById(R.id.btn_first_fragment);
//        btnFirstFragment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().popBackStack();
//                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.main_activity_container, new RestaurantFragment());
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
        ImageButton imageButtonProfile = view.findViewById(R.id.imageButtonProfile);
        imageButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// вызов диалогового окна для выбора изображения
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs_profile", Context.MODE_PRIVATE);
        String imagePath = sharedPreferences.getString("profile_picture_path", null);

// отображение сохраненного изображения на месте круглой иконки
        if(imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageButtonProfile = view.findViewById(R.id.imageButtonProfile);
            imageButtonProfile.setImageBitmap(bitmap);
        }

        getActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.VISIBLE);

// отображение данных последнего авторизовавшегося пользователя
        TextView textViewName = view.findViewById(R.id.textViewName_2);
        textViewName.setText(getLastLoggedInUser().getName());

        TextView textViewEmail = view.findViewById(R.id.editTextEmail_2);
        textViewEmail.setText(getLastLoggedInUser().getEmail());

        TextView textViewPassword = view.findViewById(R.id.password_2);
        textViewPassword.setText(getLastLoggedInUser().getPassword());


        return view;
    }

    // метод получения данных последнего авторизовавшегося пользователя
    private User getLastLoggedInUser() {
        return userDAO.getUserById(userId);
    }

    // метод обновления данных пользователя
    private void updateUser() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

// обновление данных пользователя в базе данных
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userDAO.updateUser(user);

// отправка результата в MainActivity
        Intent intent = new Intent();
        intent.putExtra("profile_updated", true);
        intent.putExtra("user_id", userId);
        getActivity().setResult(RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
        // получение выбранного изображения
            Uri imageUri = data.getData();

            try {
            // конvertация изображения в Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);

            // сохранение изображения в локальном хранилище
                saveImageToStorage(bitmap);

            // отображение изображения на месте круглой иконки
                ImageButton imageButtonProfile = getActivity().findViewById(R.id.imageButtonProfile);
                imageButtonProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveImageToStorage(Bitmap bitmap) {
        try {
// создание файла для сохранения изображения
            File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File imageFile = File.createTempFile(
                    "profile_picture_", ".jpg",
            storageDir);

// сохранение изображения в файл
            FileOutputStream out = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

// сохранение пути к файлу в SharedPreferences
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs_profile", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("profile_picture_path", imageFile.getAbsolutePath());
            editor.apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
