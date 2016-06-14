package com.hongoctuan.admin.ungdungxemphim.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.hongoctuan.admin.ungdungxemphim.BUS.NewAndGiftBUS;
import com.hongoctuan.admin.ungdungxemphim.R;

import java.util.ArrayList;

public class NewsAndGift extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_and_gift);
        //gọi asynstack khuyến mãi để lấy danh sách khuyến mãi.
        NewAndGiftBUS newAndGiftBUS = new NewAndGiftBUS(this);
        newAndGiftBUS.execute();
    }
}
