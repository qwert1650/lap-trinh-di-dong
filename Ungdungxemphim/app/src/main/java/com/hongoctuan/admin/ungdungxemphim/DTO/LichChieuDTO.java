package com.hongoctuan.admin.ungdungxemphim.DTO;

/**
 * Created by admin on 5/26/2016.
 */
public class LichChieuDTO {
    int id;
    String MaPhim;
    String ThoiGianChieu;
    int MaRap;

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int maPhong) {
        MaPhong = maPhong;
    }

    public int getMaRap() {
        return MaRap;
    }

    public void setMaRap(int maRap) {
        MaRap = maRap;
    }

    public String getThoiGianChieu() {
        return ThoiGianChieu;
    }

    public void setThoiGianChieu(String thoiGianChieu) {
        ThoiGianChieu = thoiGianChieu;
    }

    public String getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(String maPhim) {
        MaPhim = maPhim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int MaPhong;
}
