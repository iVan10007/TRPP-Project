package com.example.myapp_2.UI.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.myapp_2.R;

public class TableDialog extends DialogFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Dialog");
        View v = inflater.inflate(R.layout.table_dialog, null);

        Bundle bundle = getArguments();
        String strDate = bundle.getString("date");
        String strTime = bundle.getString("time");
        String strAmountOfQuests = bundle.getString("amountOfQuests");
        String strWishes = bundle.getString("wishes");
        String strName = bundle.getString("name");
        String strPhoneNumber = bundle.getString("phoneNumber");

        Button submit = v.findViewById(R.id.submit);
        TextView date = v.findViewById(R.id.date);
        TextView time = v.findViewById(R.id.time);
        TextView amountOfQuests = v.findViewById(R.id.amountOfQuests);
        TextView wishes = v.findViewById(R.id.wishes);
        TextView name = v.findViewById(R.id.personName);
        TextView phoneNumber = v.findViewById(R.id.phoneNumber);

        date.setText(strDate);
        time.setText(strTime);
        amountOfQuests.setText(strAmountOfQuests);
        wishes.setText(strWishes);
        name.setText(strName);
        phoneNumber.setText(strPhoneNumber);
        return v;
    }
}
