package com.hongoctuan.admin.ungdungxemphim;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.DTO.CommentDTO;

import java.util.ArrayList;

public class CommentCustomList extends ArrayAdapter<CommentDTO> {
    Activity context;
    ArrayList<CommentDTO> objects;
    public CommentCustomList(Activity context, int resource, ArrayList<CommentDTO> objects) {
        super(context, R.layout.customadapter_binhluan, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.customadapter_binhluan, null, true);
        TextView txt_nguoiBinhluan = (TextView) view.findViewById(R.id.txt_nguoiBinhluan);
        txt_nguoiBinhluan.setText(objects.get(position).getNguoibinhluan()+" - "+objects.get(position).getThoigian());
        TextView txt_noidungBinhluan = (TextView) view.findViewById(R.id.txt_noidungBinhluan);
        txt_noidungBinhluan.setText(objects.get(position).getNoidung());
        ImageView iv_iconuser = (ImageView) view.findViewById(R.id.iv_iconuser);
        iv_iconuser.setImageResource(R.drawable.ic_user);
        return view;
    }
}
