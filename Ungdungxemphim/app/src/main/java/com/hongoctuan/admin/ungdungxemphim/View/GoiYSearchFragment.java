package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.hongoctuan.admin.ungdungxemphim.View.listSearch_custom;

import java.util.ArrayList;

/**
 * Created by admin on 5/6/2016.
 */
public class GoiYSearchFragment extends Fragment {
    Activity context;
    String keyword;

    ArrayList<String> list_keyword = new ArrayList<String>();
    DatabaseHelper db;
    public GoiYSearchFragment(Activity context, String keyword) {
        this.context = context;
        this.keyword = keyword;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db = new DatabaseHelper(context);
        list_keyword = db.getLichSuSearch();
        View rootView = inflater.inflate(R.layout.goiysearch_fragment, container, false);
        final ListView lv_search = (ListView) rootView.findViewById(R.id.lv_goiysearch);
        final AutoCompleteTextView auto_search = (AutoCompleteTextView) context.findViewById(R.id.autoCompleteSearch);
        if(list_keyword.size() != 0) {
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list_keyword);
            listSearch_custom adapterlichsusearch = new listSearch_custom(context, R.layout.list_search_custom, list_keyword);
            lv_search.setAdapter(adapterlichsusearch);
        }

        ListView lv_searchnhieu = (ListView) rootView.findViewById(R.id.lv_searchnhieu);
        final ArrayList<String> arr = new ArrayList<String>();
        arr.add("Biệt đội đánh thuê");
        arr.add("Fast & Furious 7");
        arr.add("Fast & Furious 8");
        arr.add("KungFu Panda 3");
        arr.add("Transformers 2011");
        arr.add("Transformers 2016");
        arr.add("Fast & Furious 8");
        arr.add("KungFu Panda 1");
        arr.add("KungFu Panda 2");
        listSearch_custom adaptersearchnhieu = new listSearch_custom(context,R.layout.list_search_custom,arr);
        lv_searchnhieu.setAdapter(adaptersearchnhieu);
        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,list_keyword.get(position),Toast.LENGTH_SHORT).show();
                auto_search.setText(list_keyword.get(position));
            }
        });
        lv_searchnhieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                auto_search.setText(arr.get(position));
            }
        });
        return rootView;
    }
}
