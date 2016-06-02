package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.R;

/**
 * Created by admin on 6/2/2016.
 */
public class MyAdapterGridView extends ArrayAdapter<String> {
    Activity context;
    String[] objects;
    public MyAdapterGridView(Activity context,int resource, String[] objects ) {
        super(context, R.layout.layout_sodo_custom, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_sodo_custom, null, true);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_iconghe);
        TextView txt_ghe = (TextView) view.findViewById(R.id.txt_vitrighe);
        int maphim = context.getResources().getIdentifier("com.hongoctuan.admin.ungdungxemphim:drawable/ic_ghe", null, null);
        imageView.setImageResource(maphim);
        txt_ghe.setText(objects[position]);
        return view;
    }

    //    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imgView = context
////        if(convertView==null){
////            imgView=new ImageView(context);
////            //can chỉnh lại hình cho đẹp
////            imgView.setLayoutParams(new GridView.LayoutParams(85, 85));
////            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
////            imgView.setPadding(5, 5, 5, 5);
////        }else{
////            imgView=(ImageView) convertView;
////        }
//        //lấy đúng vị trí hình ảnh được chọn
//        //gán lại ImageResource
//        int maphim = context.getResources().getIdentifier("com.hongoctuan.admin.ungdungxemphim:drawable/ic_ghe", null, null);
//        imgView.setImageResource(maphim);
//        return imgView;
//    }
}
