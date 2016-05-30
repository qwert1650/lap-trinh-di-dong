package com.hongoctuan.admin.ungdungxemphim;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.View.Contact;
import com.hongoctuan.admin.ungdungxemphim.View.GoiYSearchFragment;
import com.hongoctuan.admin.ungdungxemphim.View.Introduce;
import com.hongoctuan.admin.ungdungxemphim.View.ListRap;
import com.hongoctuan.admin.ungdungxemphim.View.MainFragment;
import com.hongoctuan.admin.ungdungxemphim.View.NewsAndGift;
import com.hongoctuan.admin.ungdungxemphim.View.Question;
import com.hongoctuan.admin.ungdungxemphim.View.SearchFragment;
import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.View.TypeMovie;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity{
    DatabaseHelper db;

    ArrayList<String> dataArray_right=new ArrayList<String>();
    ArrayList<Object> objectArray_right=new ArrayList<Object>();
    ArrayList<String> dataArray_left=new ArrayList<String>();
    ArrayList<Object> objectArray_left=new ArrayList<Object>();
    int flagMenuLeft =0;
    int flagMenuRight = 0;

    Integer[] menu = {R.drawable.ic_home, R.drawable.ic_history,R.drawable.ic_list, R.drawable.ic_gift, R.drawable.ic_question,R.drawable.ic_intro,R.drawable.ic_contant};
    DrawerLayout mDrawerlayout;
    ListView mDrawerList_Left,mDrawerList_Right;
    ActionBarDrawerToggle mDrawerToggle;
    ImageButton imgLeftMenu,imgRightMenu;


    ListItemsAdapterLeft Left_Adapter;
    ListItemsAdapterRight Right_Adapter;

    //Search
    ImageView iv_back;
    ImageView iv_timkiem;
    AutoCompleteTextView auto_Search;
    ArrayList<String> list_autoSearch = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_timkiem = (ImageView) findViewById(R.id.iv_timkiem);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        db= new DatabaseHelper(this);
        list_autoSearch = db.getDanhSachPhim();
        auto_Search = (AutoCompleteTextView) findViewById(R.id.autoCompleteSearch);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_autoSearch);

        auto_Search.setAdapter(adapter);
        auto_Search.setThreshold(1);

        loadTrangChu();
        //===============Initialization of Variables=========================//

        mDrawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList_Left=(ListView)findViewById(R.id.drawer_list_left);
        mDrawerList_Right=(ListView)findViewById(R.id.drawer_list_right);
        imgLeftMenu=(ImageButton)findViewById(R.id.imgLeftMenu);
        imgRightMenu=(ImageButton)findViewById(R.id.imgRightMenu);
        mDrawerlayout.setDrawerListener(mDrawerToggle);


        //============== Define a Custom Header for Navigation drawer=================//


        LayoutInflater inflator=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflator.inflate(R.layout.header, null);

        imgLeftMenu=(ImageButton)v.findViewById(R.id.imgLeftMenu);
        imgRightMenu=(ImageButton)v.findViewById(R.id.imgRightMenu);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1281A9")));
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setCustomView(v);

        imgLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (mDrawerlayout.isDrawerOpen(mDrawerList_Right)) {
                    mDrawerlayout.closeDrawer(mDrawerList_Right);
                }
                if(flagMenuLeft == 0) {
                    mDrawerlayout.openDrawer(mDrawerList_Left);
                    flagMenuLeft = 1;
                }else{
                    mDrawerlayout.closeDrawer(mDrawerList_Left);
                    flagMenuLeft = 0;
                }
            }
        });


        imgRightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mDrawerlayout.isDrawerOpen(mDrawerList_Left)) {
                    mDrawerlayout.closeDrawer(mDrawerList_Left);
                }
                if(flagMenuRight == 0) {
                    mDrawerlayout.openDrawer(mDrawerList_Right);
                    flagMenuRight = 1;
                }else{
                    mDrawerlayout.closeDrawer(mDrawerList_Right);
                    flagMenuRight = 0;
                }

            }
        });

        mDrawerList_Left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(dataArray_left.get(position).equals("Giới Thiệu")){
                    Intent intent = new Intent(MainActivity.this, Introduce.class);
                    startActivity(intent);
                }else if(dataArray_left.get(position).equals("Hỏi Đáp")){
                    Intent intent = new Intent(MainActivity.this, Question.class);
                    startActivity(intent);
                }else if(dataArray_left.get(position).equals("Liên Hệ Quản Trị")){
                    Intent intent = new Intent(MainActivity.this, Contact.class);
                    startActivity(intent);
                }else if(dataArray_left.get(position).equals("Tin Mới & Khuyến Mãi")){
                    Intent intent = new Intent(MainActivity.this, NewsAndGift.class);
                    startActivity(intent);
                }else if(dataArray_left.get(position).equals("Danh Sách Rạp")){
                    Intent intent = new Intent(MainActivity.this, ListRap.class);
                    startActivity(intent);
                }
            }
        });
        Fill_LeftList();
        Fill_RightList();
        RefreshListView();
        db = new DatabaseHelper(this);

        iv_timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_back.setVisibility(View.VISIBLE);
                auto_Search.setPadding(50, 0, 50, 0);
                db.insertLichSu(auto_Search.getText().toString());
                loadTimKiem();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_back.setVisibility(View.GONE);
                auto_Search.setPadding(10, 0, 50, 0);
                loadTrangChu();
            }
        });

        auto_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_back.setVisibility(View.VISIBLE);
                auto_Search.setPadding(50, 0, 50, 0);
                loadGoiYSeach(auto_Search.getText().toString());
            }
        });
    }


    public void RefreshListView() {
        objectArray_left.clear();
        for (int i = 0; i < dataArray_left.size(); i++) {
            Object obj = new Object();
            objectArray_left.add(obj);
        }
        Log.d("object array", "" + objectArray_left.size());
        Left_Adapter = new ListItemsAdapterLeft(this,objectArray_left, 1,dataArray_left,menu);
        mDrawerList_Left.setAdapter(Left_Adapter);

        objectArray_right.clear();
        for (int i = 0; i < dataArray_right.size(); i++) {
            Object obj = new Object();
            objectArray_right.add(obj);
        }
        Log.d("object array", "" + objectArray_right.size());
        Right_Adapter = new ListItemsAdapterRight(this,objectArray_right, 1,dataArray_right);
        mDrawerList_Right.setAdapter(Right_Adapter);
    }
    public void Fill_LeftList()
    {
        dataArray_left.clear();
        dataArray_left.add("Trang Chủ");
        dataArray_left.add("Lich Chiếu Theo Rạp");
        dataArray_left.add("Danh Sách Rạp");
        dataArray_left.add("Tin Mới & Khuyến Mãi");
        dataArray_left.add("Hỏi Đáp");
        dataArray_left.add("Giới Thiệu");
        dataArray_left.add("Liên Hệ Quản Trị");

    }

    public void Fill_RightList()
    {
        dataArray_right.clear();
        dataArray_right.add("Option 1");
    }

    public void loadTrangChu() {
        Fragment fragment = new MainFragment(this);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainFragment, fragment).commit();
    }

    public void loadTimKiem() {
        Fragment fragment = new SearchFragment(this,auto_Search.getText().toString());
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainFragment, fragment).commit();
    }

    public void loadGoiYSeach(String keyword){
        Fragment fragment = new GoiYSearchFragment(this,auto_Search.getText().toString());
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainFragment, fragment).commit();
    }
}
