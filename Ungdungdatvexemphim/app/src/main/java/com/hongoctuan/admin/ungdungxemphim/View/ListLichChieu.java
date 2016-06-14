package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hongoctuan.admin.ungdungxemphim.BUS.GetLichChieuRapBUS;
import com.hongoctuan.admin.ungdungxemphim.R;

public class ListLichChieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lich_chieu);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myData");
        String marap = bundle.get("marap").toString();
        //gọi asynstack lịch chiếu.
        GetLichChieuRapBUS getLichChieuRapBUS = new GetLichChieuRapBUS(this);
        getLichChieuRapBUS.execute(marap);
    }
}
