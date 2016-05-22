package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

public class TypeMovie extends AppCompatActivity {
    DatabaseHelper db;
    ListView lv_typeMovie;
    ArrayList<MovieDTO> listMovie = new ArrayList<MovieDTO>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_movie);
        db= new DatabaseHelper(this);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("myData");
        String typeMovieId = packageFromCaller.getString("maloai");
        listMovie = db.getTypeMovie(typeMovieId);
        lv_typeMovie = (ListView) findViewById(R.id.lv_typeMovie);
        RelatedMovieCustomList adapter = new RelatedMovieCustomList(this, R.layout.activity_list_goi_yphim__custom, listMovie);
        lv_typeMovie.setAdapter(adapter);
    }
}
