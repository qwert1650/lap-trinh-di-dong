package com.hongoctuan.admin.ungdungxemphim.DTO;

/**
 * Created by admin on 5/26/2016.
 */
public class RapDTO {
    String id;
    String nameRap;

    public String getId() {
        return id;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getVung() {
        return vung;
    }

    public void setVung(String vung) {
        this.vung = vung;
    }

    String vitri;
    String vung;

    public void setId(String id) {
        this.id = id;
    }

    public String getNameRap() {
        return nameRap;
    }

    public void setNameRap(String nameRap) {
        this.nameRap = nameRap;
    }
}
