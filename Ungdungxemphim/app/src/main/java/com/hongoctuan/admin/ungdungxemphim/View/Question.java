package com.hongoctuan.admin.ungdungxemphim.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.hongoctuan.admin.ungdungxemphim.BUS.ExpandAdapter_HoiDap;
import com.hongoctuan.admin.ungdungxemphim.BUS.ExpandAdapter_LichChieu;
import com.hongoctuan.admin.ungdungxemphim.BUS.interface_getdatve;
import com.hongoctuan.admin.ungdungxemphim.DTO.HoiDapDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.VeDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://restfullapiservice.somee.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interface_getdatve rest = retrofit.create(interface_getdatve.class);
        Call<List<HoiDapDTO>> call = rest.loadtieudehoidap();
        call.enqueue(new Callback<List<HoiDapDTO>>() {
            @Override
            public void onResponse(Response<List<HoiDapDTO>> response, Retrofit retrofit) {
                ArrayList<String> arrtieude = new ArrayList<String>();
                for (int i = 0; i < response.body().size(); i++) {
                    arrtieude.add(response.body().get(i).getTieude());
                }

                ArrayList<HoiDapDTO> arrnoidung = new ArrayList<HoiDapDTO>();
                for (int i = 0; i < response.body().size(); i++) {
                    arrnoidung.add(response.body().get(i));
                }

                ArrayList<String> unique = removeDuplicates(arrtieude);
                final String[] item = new String[response.body().size()];
                for (int i = 0; i < unique.size(); i++) {
                    item[i] = response.body().get(i).getTieude();
                }
                ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandablehoidap);

                listView.setGroupIndicator(null);

                ExpandAdapter_HoiDap adapter = new ExpandAdapter_HoiDap(getApplicationContext(), item, response.body());

                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    static ArrayList<String> removeDuplicates(ArrayList<String> list) {

        // Store unique items in result.
        ArrayList<String> result = new ArrayList<>();

        // Record encountered Strings in HashSet.
        HashSet<String> set = new HashSet<>();

        // Loop over argument list.
        for (String item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }
}
