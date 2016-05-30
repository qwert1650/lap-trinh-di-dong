package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hongoctuan.admin.ungdungxemphim.BUS.GetDetailRapBUS;
import com.hongoctuan.admin.ungdungxemphim.R;

public class DetailRap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rap);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("myData");
        String tenrap = packageFromCaller.getString("tenrap");
        GetDetailRapBUS getDetailRapBUS = new GetDetailRapBUS(this);
        getDetailRapBUS.execute(tenrap);
    }
}
