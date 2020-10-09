package com.example.tubes_kelompok_d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class SearchHotel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RvHotelAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        ArrayList<HotelAdapter> hotelAdapter = new ArrayList<>();
        hotelAdapter.add(new HotelAdapter(R.drawable.horison, "Horison", "Jogja", "Rp450.000"));

        recyclerView = findViewById(R.id.rvHotel);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        adapter = new RvHotelAdapter(hotelAdapter);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}