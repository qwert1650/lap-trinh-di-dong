package com.hongoctuan.admin.ungdungxemphim.DTO;

/**
 * Created by admin on 6/10/2016.
 */
public class RatingDTO {
    String maphim;
    String danhgia;

    public String getMaphim() {
        return maphim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public void setMaphim(String maphim) {
        this.maphim = maphim;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }
}
