package com.hongoctuan.admin.ungdungxemphim;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.BUS.RealPathUtil;
import com.hongoctuan.admin.ungdungxemphim.BUS.UserPicture;
import com.hongoctuan.admin.ungdungxemphim.View.Contact;
import com.hongoctuan.admin.ungdungxemphim.View.HuyVe;
import com.hongoctuan.admin.ungdungxemphim.View.Introduce;
import com.hongoctuan.admin.ungdungxemphim.View.LichSuMuaVe;
import com.hongoctuan.admin.ungdungxemphim.View.ListRap;
import com.hongoctuan.admin.ungdungxemphim.View.MainFragment;
import com.hongoctuan.admin.ungdungxemphim.View.NewsAndGift;
import com.hongoctuan.admin.ungdungxemphim.View.Question;
import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity{
    DatabaseHelper db;
    ImageView ivtemp;
    ArrayList<String> dataArray_right=new ArrayList<String>();
    ArrayList<Object> objectArray_right=new ArrayList<Object>();
    ArrayList<String> dataArray_left=new ArrayList<String>();
    ArrayList<Object> objectArray_left=new ArrayList<Object>();
    int flagMenuLeft =0;
    int flagMenuRight = 0;

    Integer[] menu = {R.drawable.ic_home, R.drawable.ic_history,R.drawable.ic_history,R.drawable.ic_list, R.drawable.ic_gift, R.drawable.ic_question,R.drawable.ic_intro,R.drawable.ic_contant};
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
        db= new DatabaseHelper(this);

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
                }else if(dataArray_left.get(position).equals("Hủy Vé")) {
                    Intent intent = new Intent(MainActivity.this, HuyVe.class);
                    startActivity(intent);
                }
                else if(dataArray_left.get(position).equals("Lịch Sử Mua Vé")) {
                    Intent intent = new Intent(MainActivity.this, LichSuMuaVe.class);
                    startActivity(intent);
                }
            }
        });
        Fill_LeftList();
        Fill_RightList();
        RefreshListView();
        db = new DatabaseHelper(this);
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
        Right_Adapter = new ListItemsAdapterRight(this,objectArray_right, 1,dataArray_right, new ListItemsAdapterRight.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ImageView icon) {
                ivtemp = icon;
            }
        });
        mDrawerList_Right.setAdapter(Right_Adapter);
    }
    public void Fill_LeftList()
    {
        dataArray_left.clear();
        dataArray_left.add("Trang Chủ");
        dataArray_left.add("Hủy Vé");
        dataArray_left.add("Lịch Sử Mua Vé");
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

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                Uri selectedImageUri = data.getData();
                try {
                    ivtemp.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                    saveSharedPreferences(getPathLibrary(selectedImageUri));
                } catch (IOException e) {
                    Log.e(MainActivity.class.getSimpleName(), "Failed to load image", e);
                }
            }
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.msg_failed_to_get_intent_data, Toast.LENGTH_LONG).show();
        }
    }
    private String getPathLibrary(Uri _uri){
        String realPath;
        if (Build.VERSION.SDK_INT < 11)
            realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, _uri);

            // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19)
            realPath = RealPathUtil.getRealPathFromURI_API11to18(this, _uri);
            // SDK > 19 (Android 4.4)
        else
            realPath = RealPathUtil.getRealPathFromURI_API19(this, _uri);
        return realPath;
    }
    private void saveSharedPreferences(String link){
        SharedPreferences sharedPreferences = getSharedPreferences("ProfileImage", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("URI",link);
        editor.commit();
    }
}
