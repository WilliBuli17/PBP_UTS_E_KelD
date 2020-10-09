package com.example.tubes_kelompok_d;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvHotelAdapter extends RecyclerView.Adapter<RvHotelAdapter.RecycleViewHolder> {

    private ArrayList<HotelAdapter> hotelAdapters;

    public RvHotelAdapter(ArrayList<HotelAdapter>hotelAdapter) {hotelAdapters = hotelAdapter;}

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_hotel, parent, false);
        RvHotelAdapter.RecycleViewHolder holder = new RvHotelAdapter.RecycleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        HotelAdapter item = hotelAdapters.get(position);
        holder.imageView.setImageResource(item.getGambarHotel());
        holder.namahotel.setText(item.getNamaHotel());
        holder.lokasihotel.setText(item.getLokasiHotel());
        holder.hargahotel.setText(item.getHargaHotel());
    }

    @Override
    public int getItemCount() {
        return hotelAdapters.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView namahotel, lokasihotel, hargahotel;

        public RecycleViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.rvHotel);
            namahotel = itemView.findViewById(R.id.tvNamaHotel);
            lokasihotel = itemView.findViewById(R.id.tvLokasiHotel);
            hargahotel = itemView.findViewById(R.id.tvHargaHotel);
        }
    }
}
