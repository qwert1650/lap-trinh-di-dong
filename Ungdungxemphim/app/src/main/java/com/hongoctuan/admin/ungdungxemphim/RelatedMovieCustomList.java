package com.hongoctuan.admin.ungdungxemphim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.MovieDetail;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

public class RelatedMovieCustomList extends ArrayAdapter<MovieDTO> {
    Activity context;
    ArrayList<MovieDTO> objects;
    public RelatedMovieCustomList(Activity context, int resource, ArrayList<MovieDTO> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_list_goi_yphim__custom, null, true);
        TextView txt_goiyTenphim = (TextView) view.findViewById(R.id.txt_goiyTenPhim);
        TextView txt_goiyDienvien = (TextView) view.findViewById(R.id.txt_goiyDienvien);
        ImageView iv_goiyIcon = (ImageView) view.findViewById(R.id.iv_goiyIcon);
        TextView txt_goiyNoidung = (TextView) view.findViewById(R.id.txt_goiyNoidung);

        String htmlDienvien="<b><u>Diễn viên:</u></b>" +" "+ objects.get(position).getActor();
        txt_goiyDienvien.setText(Html.fromHtml(htmlDienvien));
        String htmlNoidung="<b><u>Tóm Tắt:</u></b>" +" "+ objects.get(position).getMovieSumary().substring(0,70)+"...";;
        txt_goiyNoidung.setText(Html.fromHtml(htmlNoidung));
        txt_goiyTenphim.setText(objects.get(position).getMovieName());
        int maphim = context.getResources().getIdentifier("com.hongoctuan.admin.ungdungxemphim:drawable/" + objects.get(position).getMovieId(), null, null);
        iv_goiyIcon.setImageResource(maphim);
        iv_goiyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xemphim(objects.get(position).getMovieId());
            }
        });
        txt_goiyTenphim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xemphim(objects.get(position).getMovieId());
            }
        });
        return view;
    }

    public void xemphim(String id){
        Intent intent = new Intent(context, MovieDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtra("myData",bundle);
        context.startActivity(intent);
    }

}
