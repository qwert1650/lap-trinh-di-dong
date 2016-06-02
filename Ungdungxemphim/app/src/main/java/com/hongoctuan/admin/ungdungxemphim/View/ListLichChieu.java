package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.hongoctuan.admin.ungdungxemphim.BUS.ExpandAdapter;
import com.hongoctuan.admin.ungdungxemphim.BUS.GetLichChieuRapBUS;
import com.hongoctuan.admin.ungdungxemphim.DTO.LichChieuRapDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ListLichChieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lich_chieu);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myData");
        String marap = bundle.get("marap").toString();
        GetLichChieuRapBUS getLichChieuRapBUS = new GetLichChieuRapBUS(this);
        getLichChieuRapBUS.execute(marap);
        //Toast.makeText(this,bundle.getString("marap").toString(), Toast.LENGTH_SHORT).show();
    }
}
