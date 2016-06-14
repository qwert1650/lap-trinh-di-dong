package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.hongoctuan.admin.ungdungxemphim.BUS.MyAdapterGridView;
import com.hongoctuan.admin.ungdungxemphim.BUS.interface_getdatve;
import com.hongoctuan.admin.ungdungxemphim.DTO.DatGheDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class DatVe extends AppCompatActivity {

    GridView gv_sodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);
        Intent intent = getIntent();
        Bundle packageFromCaller= intent.getBundleExtra("myData");
        String malichchieu = packageFromCaller.getString("malichchieu");
        gv_sodo = (GridView) findViewById(R.id.gv_sodo);
        final ArrayList<DatGheDTO> arr = new ArrayList<DatGheDTO>();
        //tạo ra arraylist chứa 100 ghế của rạp
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                DatGheDTO temp = new DatGheDTO();
                temp.setMaghe(i+"-"+j);
                temp.setMalichchieu(malichchieu);
                arr.add(temp);
            }
        }
        //lấy danh sách ghế đã đặt của rạp
        final ArrayList<String> result = new ArrayList<String>();
                Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://restfullapiservice.somee.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interface_getdatve rest = retrofit.create(interface_getdatve.class);

        Call<List<String>> call = rest.loadQuestions(malichchieu);
        //asynchronous call
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Response<List<String>> response, Retrofit retrofit) {
                for (int i = 0; i < response.body().size(); i++) {
                    result.add(response.body().get(i));
                }
                //truyền danh sách ghế của rạp và danh sách ghế đã đặt cho layout hiển thị.
                MyAdapterGridView ad = new MyAdapterGridView(getApplicationContext(), R.layout.customadapter_sodorap, arr, result);
                gv_sodo.setAdapter(ad);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
