package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

/**
 * Created by admin on 5/6/2016.
 */
public class listSearch_custom extends ArrayAdapter<String> {
    Activity context;
    ArrayList<String> objects;

    public listSearch_custom(Activity context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_search_custom, null, true);
        TextView txt_search = (TextView) view.findViewById(R.id.txt_namesearch);
        ImageView iv_search = (ImageView) view.findViewById(R.id.iv_linesearch);
        txt_search.setText(objects.get(position));
        iv_search.setImageResource(R.drawable.ic_line);
        return view;
    }
}
