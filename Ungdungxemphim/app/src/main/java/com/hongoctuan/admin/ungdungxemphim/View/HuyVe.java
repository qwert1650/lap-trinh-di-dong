package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.BUS.interface_getdatve;
import com.hongoctuan.admin.ungdungxemphim.DTO.DatGheDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.VeDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class HuyVe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huy_ve);
        SharedPreferences pre=getSharedPreferences("ungdungxemphim", MODE_PRIVATE);
        final String temp = pre.getString("username", "");
        if(!temp.equals("")) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://restfullapiservice.somee.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            interface_getdatve rest = retrofit.create(interface_getdatve.class);
            Call<List<VeDTO>> call = rest.loadvedadat(temp);
            call.enqueue(new Callback<List<VeDTO>>() {
                @Override
                public void onResponse(Response<List<VeDTO>> response, Retrofit retrofit) {
                    final ArrayList<VeDTO> veDTOArrayAdapter = new ArrayList<VeDTO>();
                    for (int i = 0; i < response.body().size(); i++) {
                        veDTOArrayAdapter.add(response.body().get(i));
                    }
                    ListView lv = (ListView) findViewById(R.id.lv_vedadat);
                    final CustomAdapter_HuyDatVe adapter_huyDatVe = new CustomAdapter_HuyDatVe(HuyVe.this, R.layout.customadapter_huydatve, veDTOArrayAdapter);
                    lv.setAdapter(adapter_huyDatVe);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://restfullapiservice.somee.com")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            DatGheDTO item = new DatGheDTO();
                            item.setTaikhoan(temp);
                            item.setMaghe(veDTOArrayAdapter.get(position).getMaghe());
                            item.setMalichchieu(veDTOArrayAdapter.get(position).getMalichchieu());
                            item.setTrangthai("0");
                            interface_getdatve rest = retrofit.create(interface_getdatve.class);
                            Call<DatGheDTO> call = rest.huydatve(item);
                            call.enqueue(new Callback<DatGheDTO>() {
                                @Override
                                public void onResponse(Response<DatGheDTO> response, Retrofit retrofit) {
                                    Toast.makeText(getApplicationContext(), "Đã Hủy Vé Thành Công", Toast.LENGTH_SHORT).show();
                                    veDTOArrayAdapter.remove(position);
                                    adapter_huyDatVe.notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Throwable t) {

                                }
                            });

                        }
                    });
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"Bạn chưa đăng nhập!!1",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
