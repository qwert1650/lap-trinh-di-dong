package com.hongoctuan.admin.ungdungxemphim.DTO;

import java.io.Serializable;

/**
 * Created by admin on 5/21/2016.
 */
public class KhuyenMaiDTO implements Serializable{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getNgayKM() {
        return NgayKM;
    }

    public void setNgayKM(String ngayKM) {
        NgayKM = ngayKM;
    }

    public String getNoiDungKM() {
        return NoiDungKM;
    }

    public void setNoiDungKM(String noiDungKM) {
        NoiDungKM = noiDungKM;
    }

    public String getDieuKienKM() {
        return DieuKienKM;
    }

    public void setDieuKienKM(String dieuKienKM) {
        DieuKienKM = dieuKienKM;
    }

    public String getGhiChuKM() {
        return GhiChuKM;
    }

    public void setGhiChuKM(String ghiChuKM) {
        GhiChuKM = ghiChuKM;
    }

    public String getHinhAnhKM() {
        return HinhAnhKM;
    }

    public void setHinhAnhKM(String hinhAnhKM) {
        HinhAnhKM = hinhAnhKM;
    }

    public String getThoiHanKM() {
        return ThoiHanKM;
    }

    public void setThoiHanKM(String thoiHanKM) {
        ThoiHanKM = thoiHanKM;
    }

    int id;
    String tenKM;
    String NgayKM;
    String NoiDungKM;
    String DieuKienKM;
    String GhiChuKM;
    String HinhAnhKM;
    String ThoiHanKM;
}
