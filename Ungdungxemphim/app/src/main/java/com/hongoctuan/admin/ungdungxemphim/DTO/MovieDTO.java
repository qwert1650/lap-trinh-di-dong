package com.hongoctuan.admin.ungdungxemphim.DTO;

import java.io.Serializable;

/**
 * Created by admin on 5/3/2016.
 */
public class MovieDTO implements Serializable {
    public String getMovieId() {
        return maphim;
    }

    public void setMovieId(String maphim) {
        this.maphim = maphim;
    }

    public String getMovieName() {
        return tenphim;
    }

    public void setMovieName(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getDirectorName() {
        return daodien;
    }

    public void setDirectorName(String daodien) {
        this.daodien = daodien;
    }

    public String getActor() {
        return dienvien;
    }

    public void setActor(String dienvien) {
        this.dienvien = dienvien;
    }

    public String getRateString() {
        return danhgia;
    }

    public void setRateString(String danhgia) {
        this.danhgia = danhgia;
    }

    public String getMovieSumary() {
        return tomtat;
    }

    public void setMovieSumary(String tomtat) {
        this.tomtat = tomtat;
    }

    public String getMovieUrl() {
        return url;
    }

    public void setMovieUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return theloai;
    }

    public void setCategory(String theloai) {
        this.theloai = theloai;
    }

    String theloai;
    String maphim;
    String tenphim;
    String daodien;
    String dienvien;
    String danhgia;
    String tomtat;
    String url;
}
