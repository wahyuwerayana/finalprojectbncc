package com.myproject.hyungapp.ui.volume;

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

public class VolumeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volume, container, false);

        EditText edPanjangBalok = view.findViewById(R.id.ed_balok_panjang);
        EditText edLebarBalok = view.findViewById(R.id.ed_balok_lebar);
        EditText edTinggiBalok = view.findViewById(R.id.ed_balok_tinggi);
        EditText edAlasPiramid = view.findViewById(R.id.ed_piramid_alas);
        EditText edTinggiPiramid = view.findViewById(R.id.ed_piramid_tinggi);
        EditText edJariTabung = view.findViewById(R.id.ed_tabung_jari);
        EditText edTinggiTabung = view.findViewById(R.id.ed_tabung_tinggi);

        TextView tvBalok = view.findViewById(R.id.tv_balok);
        TextView tvPiramid = view.findViewById(R.id.tv_piramid);
        TextView tvTabung = view.findViewById(R.id.tv_tabung);

        Button btnBalok = view.findViewById(R.id.btn_balok);
        Button btnPiramid = view.findViewById(R.id.btn_piramid);
        Button btnTabung = view.findViewById(R.id.btn_tabung);

        btnBalok.setOnClickListener(v-> {
            String panjang = edPanjangBalok.getText().toString();
            String lebar = edLebarBalok.getText().toString();
            String tinggi = edTinggiBalok.getText().toString();

            if (panjang.contains(",") || lebar.contains(",") || tinggi.contains(",")) {
                Toast.makeText(getContext(),"Use (.)    ", Toast.LENGTH_SHORT).show();
            } else if (panjang.isEmpty() || lebar.isEmpty() || tinggi.isEmpty()) {
                Toast.makeText(getContext(),"Fill the field    ", Toast.LENGTH_SHORT).show();
            } else {
                double volume = Double.parseDouble(panjang) * Double.parseDouble(lebar) * Double.parseDouble(tinggi);
                tvBalok.setText(String.valueOf(volume));
            }
        });

        btnPiramid.setOnClickListener(v-> {
            String alas = edAlasPiramid.getText().toString();
            String tinggi = edTinggiPiramid.getText().toString();

            if (alas.contains(",") || tinggi.contains(",")) {
                Toast.makeText(getContext(),"Use (.)    ", Toast.LENGTH_SHORT).show();
            } else if (alas.isEmpty() || tinggi.isEmpty()) {
                Toast.makeText(getContext(),"Fill the field    ", Toast.LENGTH_SHORT).show();
            } else {
                double volume = (Double.parseDouble(alas) * Double.parseDouble(tinggi))/3;
                tvPiramid.setText(String.valueOf(volume));
            }
        });

        btnTabung.setOnClickListener(v-> {
            String jari = edJariTabung.getText().toString();
            String tinggi = edTinggiTabung.getText().toString();

            if (jari.contains(",") || tinggi.contains(",")) {
                Toast.makeText(getContext(),"Use (.)    ", Toast.LENGTH_SHORT).show();
            } else if (jari.isEmpty() || tinggi.isEmpty()) {
                Toast.makeText(getContext(),"Fill the field    ", Toast.LENGTH_SHORT).show();
            } else {
                double volume = 3.14*Double.parseDouble(jari)*Double.parseDouble(jari)*Double.parseDouble(tinggi);
                tvTabung.setText(String.valueOf(volume));
            }
        });

        return  view;
    }
}