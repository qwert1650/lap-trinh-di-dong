package com.hongoctuan.admin.ungdungxemphim;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/16/2016.
 */
public class ListItemsAdapterLeft extends ArrayAdapter<Object>
{
    ViewHolder holder1;
    Activity contextCha;
    ArrayList<String> dataArray_left;
    Integer[] menu;

    public ListItemsAdapterLeft(Activity contextCha, List<Object> items, int x, ArrayList<String> dataArray_left, Integer[] menu) {
        // TODO Auto-generated constructor stub
        super(contextCha, android.R.layout.simple_list_item_single_choice, items);
        this.contextCha = contextCha;
        this.dataArray_left = dataArray_left;
        this.menu = menu;
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return dataArray_left.get(position);
    }

    public int getItemInteger(int pos)
    {
        return pos;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dataArray_left.size();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=contextCha.getLayoutInflater();
        convertView=inflator.inflate(R.layout.menu_left, null);
        holder1=new ViewHolder();
        holder1.text=(TextView)convertView.findViewById(R.id.txtData);
        holder1.iv=(ImageView)convertView.findViewById(R.id.imgView);
        convertView.setTag(holder1);
        String text=dataArray_left.get(position);
        holder1.text.setText(dataArray_left.get(position));
        holder1.iv.setBackgroundResource(menu[position]);
        return convertView;
    }
}