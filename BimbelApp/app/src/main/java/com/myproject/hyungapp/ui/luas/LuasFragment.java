package com.myproject.hyungapp.ui.luas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.hyungapp.R;

public class LuasFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_luas, container, false);

        EditText edPanjangPersegi = view.findViewById(R.id.ed_persegi_panjang);
        EditText edLebarPersegi = view.findViewById(R.id.ed_persegi_lebar);
        EditText edAlasSegitiga = view.findViewById(R.id.ed_segitiga_alas);
        EditText edTinggiSegitiga = view.findViewById(R.id.ed_segitiga_tinggi);
        EditText edJariLingkaran = view.findViewById(R.id.ed_lingkaran);

        TextView tvPersegi = view.findViewById(R.id.tv_persegi);
        TextView tvSegitiga = view.findViewById(R.id.tv_segitiga);
        TextView tvLingkaran = view.findViewById(R.id.tv_lingkaran);

        Button btnPersegi = view.findViewById(R.id.btn_persegi);
        Button btnSegitiga = view.findViewById(R.id.btn_segitiga);
        Button btnLingkaran = view.findViewById(R.id.btn_lingkaran);

        btnPersegi.setOnClickListener(v-> {
            String panjang = edPanjangPersegi.getText().toString();
            String lebar = edLebarPersegi.getText().toString();

            if (panjang.contains(",") || lebar.contains(",")) {
                Toast.makeText(getContext(),"Use (.)    ", Toast.LENGTH_SHORT).show();
            } else if (panjang.isEmpty() || lebar.isEmpty()) {
                Toast.makeText(getContext(),"Fill the field    ", Toast.LENGTH_SHORT).show();
            } else {
                double luas = Double.parseDouble(panjang) * Double.parseDouble(lebar);
                tvPersegi.setText(String.valueOf(luas));
            }
        });

        btnSegitiga.setOnClickListener(v-> {
            String alas = edAlasSegitiga.getText().toString();
            String tinggi = edTinggiSegitiga.getText().toString();

            if (alas.contains(",") || tinggi.contains(",")) {
                Toast.makeText(getContext(),"Use (.)    ", Toast.LENGTH_SHORT).show();
            } else if (alas.isEmpty() || tinggi.isEmpty()) {
                Toast.makeText(getContext(),"Fill the field    ", Toast.LENGTH_SHORT).show();
            } else {
                double luas = (Double.parseDouble(alas) * Double.parseDouble(tinggi))/2;
                tvSegitiga.setText(String.valueOf(luas));
            }
        });

        btnLingkaran.setOnClickListener(v-> {
            String jari = edJariLingkaran.getText().toString();

            if (jari.contains(",")) {
                Toast.makeText(getContext(),"Use (.)    ", Toast.LENGTH_SHORT).show();
            } else if (jari.isEmpty()) {
                Toast.makeText(getContext(),"Fill the field    ", Toast.LENGTH_SHORT).show();
            } else {
                double luas = Double.parseDouble(jari) * Double.parseDouble(jari) * 3.14;
                tvLingkaran.setText(String.valueOf(luas));
            }
        });

        return view;
    }
}