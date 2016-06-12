package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.DTO.DatGheDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by admin on 6/2/2016.
 */
public class MyAdapterGridView extends ArrayAdapter<DatGheDTO> {
    Context context;
    ArrayList<DatGheDTO> objects;
    ArrayList<String> result;
    public MyAdapterGridView(Context context,int resource, ArrayList<DatGheDTO> objects, ArrayList<String> result) {
        super(context, R.layout.customadapter_sodorap, objects);
        this.context = context;
        this.objects = objects;
        this.result = result;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.customadapter_sodorap, null);
        }
        final ImageView imageView = (ImageView) view.findViewById(R.id.iv_iconghe);
        TextView txt_ghe = (TextView) view.findViewById(R.id.txt_vitrighe);
        final int[] maphim = {0};
        if(result.contains(objects.get(position).getMaghe())){
            maphim[0] = context.getResources().getIdentifier("com.hongoctuan.admin.ungdungxemphim:drawable/ic_ghedadat", null, null);
        }else {
            maphim[0] = context.getResources().getIdentifier("com.hongoctuan.admin.ungdungxemphim:drawable/ic_ghe", null, null);
        }
        imageView.setImageResource(maphim[0]);
        txt_ghe.setText(objects.get(position).getMaghe());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pre=context.getSharedPreferences("ungdungxemphim", context.MODE_PRIVATE);
                String temp = pre.getString("username", "");
                DatGheDTO dv = new DatGheDTO();
                dv.setMalichchieu(objects.get(position).getMalichchieu());
                dv.setMaghe(objects.get(position).getMaghe());
                dv.setTaikhoan(pre.getString("username", ""));
                dv.setTrangthai("1");
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://restfullapiservice.somee.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                interface_getdatve rest = retrofit.create(interface_getdatve.class);
                Call<DatGheDTO> call = rest.datghe(dv);
                call.enqueue(new Callback<DatGheDTO>() {
                    @Override
                    public void onResponse(Response<DatGheDTO> response, Retrofit retrofit) {
                        int temp = context.getResources().getIdentifier("com.hongoctuan.admin.ungdungxemphim:drawable/ic_ghecho", null, null);
                        imageView.setImageResource(temp);
                        Toast.makeText(context,"Đặt vé thành công!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });
        return view;
    }
}
