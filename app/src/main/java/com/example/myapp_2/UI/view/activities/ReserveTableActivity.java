package com.example.myapp_2.UI.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


import com.example.myapp_2.Data.Discount_Get_table.DatePickerFragment;
import com.example.myapp_2.R;
import com.example.myapp_2.databinding.ActivityReserveTableBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class ReserveTableActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    Dialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReserveTableBinding binding = ActivityReserveTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String restaurantName = intent.getStringExtra("restaurantName");
        binding.restaurantName.setText(restaurantName);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });

        binding.pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.times_of_day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.pickTime.setAdapter(adapter);
        binding.pickTime.setOnItemSelectedListener(this);

        binding.sendAnApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(v);
            }
        });

        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(binding.amountQuests.getText().toString());
                if(number > 1){
                    binding.amountQuests.setText(Integer.toString(number-1));
                }
            }
        });
        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(binding.amountQuests.getText().toString());
                binding.amountQuests.setText(Integer.toString(number+1));
            }
        });

    }

    private void showCustomDialog(View v) {
        dialog.setContentView(R.layout.table_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button submit = dialog.findViewById(R.id.submit);
        Button close = dialog.findViewById(R.id.close);
        TextView date = dialog.findViewById(R.id.date);
        TextView time = dialog.findViewById(R.id.time);
        TextView amountOfQuests = dialog.findViewById(R.id.amountOfQuests);
        TextView wishes = dialog.findViewById(R.id.wishes);
        TextView name = dialog.findViewById(R.id.person_name);
        TextView phoneNumber = dialog.findViewById(R.id.phoneNumber);

        TextView date1 = v.findViewById(R.id.date);
        TextView time1 = v.findViewById(R.id.time);
        TextView amountOfQuests1 = v.findViewById(R.id.amountOfQuests);
        TextView wishes1 = v.findViewById(R.id.wishes);
        TextView name1 = v.findViewById(R.id.person_name);
        TextView phoneNumber1 = v.findViewById(R.id.phoneNumber);

        date.setText(date1.getText());
        time.setText(time1.getText());
        amountOfQuests.setText(amountOfQuests1.getText());
        wishes.setText(wishes1.getText());
        name.setText(name1.getText());
        phoneNumber.setText(phoneNumber1.getText());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.date);
        textView.setText(currentDateString);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}