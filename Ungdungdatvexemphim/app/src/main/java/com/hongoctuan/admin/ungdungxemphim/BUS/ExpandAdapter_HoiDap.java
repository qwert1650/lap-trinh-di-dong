package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.DTO.HoiDapDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.LichChieuRapDTO;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.hongoctuan.admin.ungdungxemphim.View.DatVe;

import java.util.ArrayList;
import java.util.List;

public class ExpandAdapter_HoiDap extends BaseExpandableListAdapter {

	private Context context;
	String[] item;
	List<HoiDapDTO> listlichchieu;
	public ExpandAdapter_HoiDap(Context context, String[] item, List<HoiDapDTO> listlichchieu) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.item = item;
		this.listlichchieu = listlichchieu;
	}	
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return item.length;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		int dem =0;
		for(int i = 0; i < listlichchieu.size(); i++){
			if(listlichchieu.get(i).getTieude().equals(item[groupPosition])){
				dem++;
			}
		}
		return dem;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if(v == null){
			v = LayoutInflater.from(context).
					inflate(R.layout.layout_group_list, parent, false);
		}
		//gán giá trị cho các textview hiển thị parent node.
		TextView textView = (TextView) v.findViewById(R.id.textGroup);
		textView.setText(item[groupPosition]);
		
		int ic_arrow = isExpanded ? 
				android.R.drawable.arrow_up_float:
					android.R.drawable.arrow_down_float; 
		ImageView imgArrow = (ImageView)v.findViewById(R.id.imgArrow);
		
		int bgColor = isExpanded ? Color.GRAY
				: Color.WHITE;
		v.setBackgroundColor(bgColor);
		
		imgArrow.setImageResource(ic_arrow);
		
		return v;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent){
		// TODO Auto-generated method stub
		View v = convertView;
		if(v == null){
			v = LayoutInflater.from(context).
					inflate(R.layout.layout_item_list, parent, false);
		}
		final Button btnitem = (Button) v.findViewById(R.id.text);
		int dem =0;
		//lấy danh sách child node theo mỗi parent node.
		for(int i = 0; i < listlichchieu.size(); i++){
			if(listlichchieu.get(i).getTieude().equals(item[groupPosition])){
				dem++;
			}
		}
		String[] noidung = new String[dem];
		int k =0;
		// lấy thông tin của mỗi child node trong danh sách parent node đã lọc phía trên.
		for(int i = 0; i < listlichchieu.size(); i++){
			if(listlichchieu.get(i).getTieude().equals(item[groupPosition])){
				noidung[k] = listlichchieu.get(i).getNoidung();
				k++;
			}
		}
		//gán thông tin mỗi child node đã lấy phía trên cho button.
		btnitem.setText(noidung[childPosition]);
		return v;
	}

	
	
	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
