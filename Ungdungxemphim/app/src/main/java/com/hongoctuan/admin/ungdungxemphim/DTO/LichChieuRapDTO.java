package com.hongoctuan.admin.ungdungxemphim.DTO;

/**
 * Created by admin on 5/31/2016.
 */
public class LichChieuRapDTO {
    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getThoigianchieu() {
        return thoigianchieu;
    }

    public void setThoigianchieu(String thoigianchieu) {
        this.thoigianchieu = thoigianchieu;
    }

    public String getKythuat() {
        return kythuat;
    }

    public void setKythuat(String kythuat) {
        this.kythuat = kythuat;
    }

    public String getMalichchieu() {
        return malichchieu;
    }

    public void setMalichchieu(String malichchieu) {
        this.malichchieu = malichchieu;
    }

    public String getMarap() {
        return marap;
    }

    public void setMarap(String marap) {
        this.marap = marap;
    }

    String tenphim;
    String thoigianchieu;
    String kythuat;
    String malichchieu;
    String marap;

    public String getGiave() {
        return giave;
    }

    public void setGiave(String giave) {
        this.giave = giave;
    }

    String giave;
}
