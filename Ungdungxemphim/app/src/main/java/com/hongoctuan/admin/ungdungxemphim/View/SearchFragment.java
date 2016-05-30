package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

/**
 * Created by admin on 5/5/2016.
 */
public class SearchFragment extends Fragment {
    ArrayList<MovieDTO> listPhim = new ArrayList<MovieDTO>();
    DatabaseHelper db;
    Activity context;
    String tenphim="";
    public SearchFragment(Activity context, String tenphim) {
        this.context = context;
        this.tenphim = tenphim;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db = new DatabaseHelper(context);
        //listPhim = db.getSearchPhim(tenphim);
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);
        ListView lv_search = (ListView) rootView.findViewById(R.id.lv_search);
        if(listPhim.size() == 0){
            Toast.makeText(context,"Không tìm thấy phim!", Toast.LENGTH_SHORT).show();
        }else {
            RelatedMovieCustomList adapter = new RelatedMovieCustomList(context, R.layout.activity_list_goi_yphim__custom, listPhim);
            lv_search.setAdapter(adapter);
        }
        return rootView;
    }
}
