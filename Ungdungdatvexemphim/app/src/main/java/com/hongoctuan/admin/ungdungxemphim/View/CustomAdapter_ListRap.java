package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

/**
 * Created by admin on 6/8/2016.
 */
public class CustomAdapter_ListRap extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> objects;
    public CustomAdapter_ListRap(Context context, int resource, ArrayList<String> objects) {
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
            view = vi.inflate(R.layout.customadapter_listrap, null);
        }
        TextView txt_listrap = (TextView) view.findViewById(R.id.txt_listrap);
        ImageView iv_listrap = (ImageView) view.findViewById(R.id.ic_listrap);
        ImageView iv_listrapline = (ImageView) view.findViewById(R.id.ic_listrapline);
        iv_listrap.setImageResource(R.drawable.ic_rap);
        txt_listrap.setText(objects.get(position));
        iv_listrapline.setImageResource(R.drawable.ic_line);
        return view;
    }
}
