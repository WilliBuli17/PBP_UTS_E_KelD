package com.example.tubes_kelompok_d;

public class HotelAdapter {
    private int gambarHotel;
    private String namaHotel;
    private String lokasiHotel;
    private String hargaHotel;

    public HotelAdapter(int gambarHotel, String namaHotel, String lokasiHotel, String hargaHotel){
        this.gambarHotel = gambarHotel;
        this.namaHotel = namaHotel;
        this.lokasiHotel = lokasiHotel;
        this.hargaHotel = hargaHotel;
    }

    public int getGambarHotel() {
        return gambarHotel;
    }

    public void setGambarHotel(int gambarHotel) {
        this.gambarHotel = gambarHotel;
    }

    public String getNamaHotel() {
        return namaHotel;
    }

    public void setNamaHotel(String namaHotel) {
        this.namaHotel = namaHotel;
    }

    public String getLokasiHotel() {
        return lokasiHotel;
    }

    public void setLokasiHotel(String lokasiHotel) {
        this.lokasiHotel = lokasiHotel;
    }

    public String getHargaHotel() {
        return hargaHotel;
    }

    public void setHargaHotel(String hargaHotel) {
        this.hargaHotel = hargaHotel;
    }
}
