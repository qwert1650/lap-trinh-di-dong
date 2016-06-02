package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.BUS.MyAdapterGridView;
import com.hongoctuan.admin.ungdungxemphim.R;

public class DatVe extends AppCompatActivity {

    GridView gv_sodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);
        Intent intent = getIntent();
        Bundle packageFromCaller= intent.getBundleExtra("myData");
        String tenrap = packageFromCaller.getString("info");
        Toast.makeText(this,tenrap,Toast.LENGTH_SHORT).show();
        gv_sodo = (GridView) findViewById(R.id.gv_sodo);
        String[] arr = new String [100];
        int dem =0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                arr[dem] = i+"-"+j;
                dem++;
            }
        }
//        String arr[]={"Ipad","Iphone","New Ipad",
//                "SamSung","Nokia","Sony Ericson",
//                "LG","Q-Mobile","HTC","Blackberry",
//                "G Phone","FPT - Phone","HK Phone"
//        };
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        MyAdapterGridView adapter = new MyAdapterGridView(this,R.layout.layout_sodo_custom,arr);
        gv_sodo.setAdapter(adapter);
    }
}
