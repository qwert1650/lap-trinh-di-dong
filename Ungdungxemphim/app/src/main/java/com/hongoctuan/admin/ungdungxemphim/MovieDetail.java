package com.hongoctuan.admin.ungdungxemphim;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.BUS.GetMoviesDetailBUS;
import com.hongoctuan.admin.ungdungxemphim.BUS.interface_getdatve;
import com.hongoctuan.admin.ungdungxemphim.DTO.RatingDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.SumRatingDTO;
import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.DTO.CommentDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MovieDetail extends Activity {
    DatabaseHelper db;

    ArrayList<CommentDTO> list_binhluan;
    ListView lv_binhluan;
    Button btnComment,btn_sharefb,btn_binhchon;
    EditText editComment;
    RatingBar ratingBar;
    String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_moviel);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("myData");
        id = packageFromCaller.getString("id");
        db = new DatabaseHelper(this);
        list_binhluan = new ArrayList<CommentDTO>();

        lv_binhluan = (ListView) findViewById(R.id.lv_binhluan);
        btnComment = (Button) findViewById(R.id.btnComment);
        editComment = (EditText) findViewById(R.id.editComment);
        btn_sharefb = (Button) findViewById(R.id.btn_sharefb);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btn_binhchon = (Button) findViewById(R.id.btn_binhchon);

        editComment.setSelected(false);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        Drawable progress = ratingBar.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.WHITE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://restfullapiservice.somee.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final interface_getdatve rest = retrofit.create(interface_getdatve.class);

        Call<List<CommentDTO>> callcomment = rest.getbinhluan(id);
        callcomment.enqueue(new Callback<List<CommentDTO>>() {
            @Override
            public void onResponse(Response<List<CommentDTO>> response, Retrofit retrofit) {
                for (int i = 0; i < response.body().size(); i++) {
                    list_binhluan.add(response.body().get(i));

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        final CommentCustomList binhluanAdapter = new CommentCustomList(this,R.layout.customadapter_binhluan,list_binhluan);
        lv_binhluan.setAdapter(binhluanAdapter);
        Call<SumRatingDTO> call = rest.loadrating(id);
        call.enqueue(new Callback<SumRatingDTO>() {
            @Override
            public void onResponse(Response<SumRatingDTO> response, Retrofit retrofit) {
                if(response.body()!= null) {
                    float kqrating = (response.body().getTongdiem() * 5) / (5 * response.body().getTongso());
                    ratingBar.setRating(kqrating);
                    binhluanAdapter.notifyDataSetChanged();
                }else{
                    ratingBar.setRating(0);
                    binhluanAdapter.notifyDataSetChanged();
                    binhluanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        GetMoviesDetailBUS getMovieDetailBUS = new GetMoviesDetailBUS(this);
        getMovieDetailBUS.execute(id);
        binhluanAdapter.notifyDataSetChanged();

        btn_sharefb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String extraText = "www.hcmus.edu.vn";
                share.putExtra(Intent.EXTRA_TEXT, extraText);
                startActivity(Intent.createChooser(share, "Share with Friends"));
            }
        });

        btn_binhchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingDTO item = new RatingDTO();
                item.setId("1");
                item.setDanhgia(ratingBar.getRating() + "");
                item.setMaphim(id);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://restfullapiservice.somee.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                interface_getdatve rest = retrofit.create(interface_getdatve.class);
                Call<RatingDTO> call = rest.postrating(item);
                call.enqueue(new Callback<RatingDTO>() {
                    @Override
                    public void onResponse(Response<RatingDTO> response, Retrofit retrofit) {
                        btn_binhchon.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "Cám ơn bạn đã bình chọn cho chúng tôi!!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pre = getSharedPreferences("ungdungxemphim", MODE_PRIVATE);
                String username = pre.getString("username", "");
                if (username == "") {
                    username = "Khách";
                }
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String time = df.format(cal.getTime());
                CommentDTO item = new CommentDTO();
                item.setMaphim(id);
                item.setThoigian(time);
                item.setNoidung(editComment.getText().toString());
                item.setNguoibinhluan(username);
                list_binhluan.add(item);
                binhluanAdapter.notifyDataSetChanged();
                Call<CommentDTO> call = rest.postbinhluan(item);
                call.enqueue(new Callback<CommentDTO>() {
                    @Override
                    public void onResponse(Response<CommentDTO> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(), "Bạn vừa thêm một bình luận!!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });
    }
}
