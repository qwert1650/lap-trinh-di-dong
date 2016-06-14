package com.hongoctuan.admin.ungdungxemphim.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.BUS.interface_getdatve;
import com.hongoctuan.admin.ungdungxemphim.DTO.GioiThieuDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.HoiDapDTO;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Introduce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        final ImageView iv_gioithieu = (ImageView) findViewById(R.id.iv_gioithieu);
        final TextView txt_gioithieu = (TextView) findViewById(R.id.txt_gioithieu);
        //lấy thông tin giới thiệu.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://restfullapiservice.somee.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interface_getdatve rest = retrofit.create(interface_getdatve.class);
        Call<GioiThieuDTO> call = rest.loadgioithieu();
        call.enqueue(new Callback<GioiThieuDTO>() {
            @Override
            public void onResponse(Response<GioiThieuDTO> response, Retrofit retrofit) {
                txt_gioithieu.setText(response.body().getNoidung());
                //lấy hình cho phần giới thiệu
                Picasso.with(getApplicationContext()).load(response.body().getHinhanh()).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gioithieu);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
