package com.hongoctuan.admin.ungdungxemphim.View;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.hongoctuan.admin.ungdungxemphim.BUS.ExpandAdapter_DatVe;
import com.hongoctuan.admin.ungdungxemphim.BUS.ExpandAdapter_HoiDap;
import com.hongoctuan.admin.ungdungxemphim.BUS.interface_getdatve;
import com.hongoctuan.admin.ungdungxemphim.DTO.HoiDapDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.LichChieuDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BookTicket extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lich_chieu);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("myData");
        final MovieDTO maphim = (MovieDTO) packageFromCaller.getSerializable("maphim");
        final String kythuat = packageFromCaller.getString("kythuat");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://restfullapiservice.somee.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interface_getdatve rest = retrofit.create(interface_getdatve.class);
        Call<List<LichChieuDTO>> call = rest.getlichchieuphim(maphim.getMovieId(), kythuat);
        call.enqueue(new Callback<List<LichChieuDTO>>() {
            @Override
            public void onResponse(Response<List<LichChieuDTO>> response, Retrofit retrofit) {
                ArrayList<String> arrtenrap = new ArrayList<String>();
                for (int i = 0; i < response.body().size(); i++) {
                    arrtenrap.add(response.body().get(i).getTenrap());
                }

                ArrayList<LichChieuDTO> arrlichchieu = new ArrayList<LichChieuDTO>();
                for (int i = 0; i < response.body().size(); i++) {
                    arrlichchieu.add(response.body().get(i));
                }

                ArrayList<String> unique = removeDuplicates(arrtenrap);
                final String[] item = new String[unique.size()];
                for (int i = 0; i < unique.size(); i++) {
                    item[i] = response.body().get(i).tenrap;
                }
                ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView1);

                listView.setGroupIndicator(null);

                ExpandAdapter_DatVe adapter = new ExpandAdapter_DatVe(BookTicket.this, item, response.body());

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
