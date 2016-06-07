package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.BUS.interface_getdatve;
import com.hongoctuan.admin.ungdungxemphim.DTO.VeDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LichSuMuaVe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_ve);
        SharedPreferences pre=getSharedPreferences("ungdungxemphim", MODE_PRIVATE);
        final String temp = pre.getString("username", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://restfullapiservice.somee.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        interface_getdatve rest = retrofit.create(interface_getdatve.class);
        Call<List<VeDTO>> call = rest.loadlichsudatve(temp);
        call.enqueue(new Callback<List<VeDTO>>() {
            @Override
            public void onResponse(Response<List<VeDTO>> response, Retrofit retrofit) {
                final ArrayList<VeDTO> arrlichsu = new ArrayList<VeDTO>();
                for(int i = 0;i < response.body().size(); i++){
                    arrlichsu.add(response.body().get(i));
                }
                ListView lv_lichsu = (ListView) findViewById(R.id.lv_lichsumuave);
                final CustomAdapter_HuyDatVe adapter = new CustomAdapter_HuyDatVe(LichSuMuaVe.this,R.layout.customadapter_lichsudatve,arrlichsu);
                lv_lichsu.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
