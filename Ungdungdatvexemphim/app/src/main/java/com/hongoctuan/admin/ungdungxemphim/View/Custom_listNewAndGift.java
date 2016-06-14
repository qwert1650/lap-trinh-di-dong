package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.hongoctuan.admin.ungdungxemphim.DTO.KhuyenMaiDTO;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 5/21/2016.
 */
public class Custom_listNewAndGift extends ArrayAdapter<KhuyenMaiDTO> {
    Activity context;
    ArrayList<KhuyenMaiDTO> objects;

    public Custom_listNewAndGift(Activity context, int resource, ArrayList<KhuyenMaiDTO> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.customadapter_listnewandgift, null, true);
        ImageView iv_listhinhkhuyenmai = (ImageView) view.findViewById(R.id.iv_listhinhkhuyenmai);
        //lấy hình của thông tin khuyến mãi.
        Picasso.with(context).load(objects.get(position).getHinhAnhKM()).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_listhinhkhuyenmai);
        return view;
    }
}
