package com.example.tubes_kelompok_d;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment {
    CarouselView carouselView;
    AutoCompleteTextView kota, dewasa, anak, kamar;
    TextInputEditText tglCheckIn, tglCheckOut;
    DatePickerDialog datePickerDialog;

    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = (CarouselView) v.findViewById(R.id.carouselView);
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(sampleImages.length);

        kota = v.findViewById(R.id.kota_text);
        dewasa = v.findViewById(R.id.dewasa_text);
        anak = v.findViewById(R.id.anak_text);
        kamar = v.findViewById(R.id.kamar_text);
        tglCheckIn = v.findViewById(R.id.checkInInput);
        tglCheckOut = v.findViewById(R.id.checkOutInput);

        ArrStr();

        tglCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int years, int months, int days) {
                        tglCheckIn.setText(days + "/" + (months + 1) + "/" + years);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        tglCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int years, int months, int days) {
                        tglCheckOut.setText(days + "/" + (months + 1) + "/" + years);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        return v;
    }

    private void ArrStr() {
        final ArrayAdapter<String> arrayAdapterKota = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.kota_text));
        arrayAdapterKota.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        kota.setAdapter(arrayAdapterKota);

        final ArrayAdapter<String> arrayAdapterDewasa = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.dewasa_text));
        arrayAdapterDewasa.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        dewasa.setAdapter(arrayAdapterDewasa);

        final ArrayAdapter<String> arrayAdapterAnak = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.anak_text));
        arrayAdapterAnak.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        anak.setAdapter(arrayAdapterAnak);

        final ArrayAdapter<String> arrayAdapterKamar = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.kamar_text));
        arrayAdapterKamar.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        kamar.setAdapter(arrayAdapterKamar);
    }
}