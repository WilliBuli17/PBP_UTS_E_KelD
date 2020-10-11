package com.example.tubes_kelompok_d;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import static com.example.tubes_kelompok_d.Navbar.EMAIL;
import static com.example.tubes_kelompok_d.Navbar.NAME;
import static com.example.tubes_kelompok_d.Navbar.PHONE;

public class SearchFragment extends Fragment {

    String getNama, getEmail, getPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        final MaterialTextView message = v.findViewById(R.id.namaView);
        final TextView email = v.findViewById(R.id.emailView);
        final TextView phone = v.findViewById(R.id.phoneView);

        getNama = getArguments().getString(NAME);
        getEmail = getArguments().getString(EMAIL);
        getPhone = getArguments().getString(PHONE);

        Toast.makeText(getActivity(),getNama+" | "+getEmail+" | "+getPhone,Toast.LENGTH_SHORT).show();

        String check= "Try Again!";
        message.setTextColor(Color.WHITE);
        message.setBackgroundColor(Color.RED);
        message.setText(check);

        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}
