package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hongoctuan.admin.ungdungxemphim.BUS.GetRapBUS;
import com.hongoctuan.admin.ungdungxemphim.BUS.GetTimeShowBUS;
import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

public class BookTicket extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("myData");
        MovieDTO phim = (MovieDTO) packageFromCaller.getSerializable("maphim");
        String kythuat = packageFromCaller.getString("kythuat");
        GetRapBUS getRapBUS = new GetRapBUS(this);
        getRapBUS.execute(phim.getMovieId());
        GetTimeShowBUS getTimeShowBUS = new GetTimeShowBUS(this);
        sharedPreferences = getSharedPreferences("datvexemphim", Context.MODE_PRIVATE);
        String marap = sharedPreferences.getString("marap", "");
        String[] arr = {phim.getMovieId(),marap,kythuat};
        getTimeShowBUS.execute(arr);
    }
}
