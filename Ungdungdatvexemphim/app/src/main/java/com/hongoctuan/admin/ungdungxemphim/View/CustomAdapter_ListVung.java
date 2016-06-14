package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 6/8/2016.
 */
public class CustomAdapter_ListVung extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> objects;
    public CustomAdapter_ListVung(Context context, int resource, ArrayList<String> objects) {
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
            view = vi.inflate(R.layout.customadapter_listvung, null);
        }
        TextView txt_listvung = (TextView) view.findViewById(R.id.txt_listvung);
        ImageView iv_listvung = (ImageView) view.findViewById(R.id.ic_listvung);
        ImageView iv_listvungline = (ImageView) view.findViewById(R.id.ic_listvungline);
        iv_listvung.setImageResource(R.drawable.ic_flag);
        txt_listvung.setText(objects.get(position));
        iv_listvungline.setImageResource(R.drawable.ic_line);
        return view;
    }
}
