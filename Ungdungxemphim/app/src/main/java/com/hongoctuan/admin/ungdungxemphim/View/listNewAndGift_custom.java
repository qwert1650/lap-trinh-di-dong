package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.BUS.GetImageKhuyenMaiBUS;
import com.hongoctuan.admin.ungdungxemphim.DTO.KhuyenMaiDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

/**
 * Created by admin on 5/21/2016.
 */
public class listNewAndGift_custom extends ArrayAdapter<KhuyenMaiDTO> {
    Activity context;
    ArrayList<KhuyenMaiDTO> objects;

    public listNewAndGift_custom(Activity context, int resource, ArrayList<KhuyenMaiDTO> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_newandgift_custom, null, true);
        TextView txtTenKM = (TextView) view.findViewById(R.id.txtTenKM);
        txtTenKM.setText(objects.get(position).getTenKM());
        TextView txtNgayKM = (TextView) view.findViewById(R.id.txtNgayKM);
        txtNgayKM.setText(objects.get(position).getNgayKM());
        LinearLayout llHinhAnhKM = (LinearLayout) view.findViewById(R.id.llkhuyenmai);
        GetImageKhuyenMaiBUS getImageBUS = new GetImageKhuyenMaiBUS(context,llHinhAnhKM);
        getImageBUS.execute(objects.get(position).getHinhAnhKM());
        return view;
    }
}
