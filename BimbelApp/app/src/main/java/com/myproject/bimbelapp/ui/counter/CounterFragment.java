package com.myproject.bimbelapp.ui.counter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myproject.bimbelapp.R;


public class CounterFragment extends Fragment {

    private TextView tvDisplay;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_counter, container, false);


        FloatingActionButton btnIncrease = view.findViewById(R.id.fab_plus);
        FloatingActionButton btnDecrease = view.findViewById(R.id.fab_min);
        Button btnReset = view.findViewById(R.id.btn_reset);
         tvDisplay = view.findViewById(R.id.tv_display);

        sharedPreferences = requireContext().getSharedPreferences("counter", Context.MODE_PRIVATE);

        tvDisplay.setText(String.valueOf(sharedPreferences.getInt("number", 0)));

        btnIncrease.setOnClickListener(v-> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String currentValue = tvDisplay.getText().toString();
            int value = Integer.parseInt(currentValue);
            value++;
            editor.putInt("number", value);
            editor.apply();
            tvDisplay.setText(String.valueOf(value));
        });

        btnDecrease.setOnClickListener(v-> {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String currentValue = tvDisplay.getText().toString();
                int value = Integer.parseInt(currentValue);
                if (value >= 1){
                    value--;
                    editor.putInt("number", value);
                    editor.apply();
                    tvDisplay.setText(String.valueOf(value));
                }
        });

        btnReset.setOnClickListener(v-> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            tvDisplay.setText("0");
        });

        return  view;
    }
}