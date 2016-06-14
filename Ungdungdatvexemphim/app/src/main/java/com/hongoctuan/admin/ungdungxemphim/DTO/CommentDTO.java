package com.hongoctuan.admin.ungdungxemphim.DTO;

/**
 * Created by admin on 5/3/2016.
 */
public class CommentDTO {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaphim() {
        return maphim;
    }

    public void setMaphim(String maphim) {
        this.maphim = maphim;
    }

    public String getNguoibinhluan() {
        return nguoibinhluan;
    }

    public void setNguoibinhluan(String nguoibinhluan) {
        this.nguoibinhluan = nguoibinhluan;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    String id;
    String maphim;
    String nguoibinhluan;
    String noidung;
    String thoigian;
}
