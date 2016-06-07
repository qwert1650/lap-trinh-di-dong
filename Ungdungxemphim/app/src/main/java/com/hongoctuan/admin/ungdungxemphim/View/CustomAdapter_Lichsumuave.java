package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.DTO.VeDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

/**
 * Created by admin on 6/7/2016.
 */
public class CustomAdapter_Lichsumuave extends ArrayAdapter<VeDTO> {
    Activity context;
    ArrayList<VeDTO> objects;
    public CustomAdapter_Lichsumuave(Activity context, int resource, ArrayList<VeDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.customadapter_lichsudatve, null);
        }
        TextView txt_vetenphim = (TextView) view.findViewById(R.id.txt_lstenphim);
        TextView txt_vegiochieu = (TextView) view.findViewById(R.id.txt_lsgiochieu);
        TextView txt_vekythuat = (TextView) view.findViewById(R.id.txt_lskythuat);
        TextView txt_vemaghe = (TextView) view.findViewById(R.id.txt_lsmaghe);
        TextView txt_vephong = (TextView) view.findViewById(R.id.txt_lsphong);
        TextView txt_verap = (TextView) view.findViewById(R.id.txt_lsrap);
        txt_vegiochieu.setText(objects.get(position).getThoigian().toString());
        txt_vekythuat.setText(objects.get(position).getKythuat().toString());
        txt_vemaghe.setText(objects.get(position).getMaghe().toString());
        txt_vephong.setText(objects.get(position).getPhong().toString());
        txt_verap.setText(objects.get(position).getTenrap().toString());
        txt_vetenphim.setText(objects.get(position).getPhim().toString());
        return view;
    }
}