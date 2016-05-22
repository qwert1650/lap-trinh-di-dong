package com.hongoctuan.admin.ungdungxemphim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.View.WatchMovieBUS;
import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.DTO.CommentDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;

import java.util.ArrayList;

public class MovieDetail extends Activity {
    DatabaseHelper db;
    MovieDTO phim;
    TextView txt_tenphim, txt_daodien, txt_dienvien, txt_noidungphim;
    ImageView iv_phim;
    ArrayList<CommentDTO> list_binhluan;
    ListView lv_binhluan;
    Button btn_xemPhim,btnComment;
    EditText editComment;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_moviel);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("myData");
        id = packageFromCaller.getString("id");
        db = new DatabaseHelper(this);
        phim = new MovieDTO();
        phim = db.getPhim(id);
        list_binhluan = new ArrayList<CommentDTO>();


        txt_tenphim = (TextView) findViewById(R.id.txt_tenphim);
        txt_daodien = (TextView) findViewById(R.id.txt_daodien);
        txt_dienvien = (TextView) findViewById(R.id.txt_dienvien);
        iv_phim = (ImageView) findViewById(R.id.iv_phim);
        txt_noidungphim = (TextView) findViewById(R.id.txt_noidungphim);
        lv_binhluan = (ListView) findViewById(R.id.lv_binhluan);
        btn_xemPhim = (Button) findViewById(R.id.btn_xemphim);
        btnComment = (Button) findViewById(R.id.btnComment);
        editComment = (EditText) findViewById(R.id.editComment);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        Drawable progress = ratingBar.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.WHITE);

        txt_tenphim.setText(phim.getMovieName());
        String htmlDaodien="<b><u>Đạo diễn:</u></b>" +" "+ phim.getDirectorName();
        txt_daodien.setText(Html.fromHtml(htmlDaodien));
        String htmlDienvien="<b><u>Diễn viên:</u></b>"+" "+ phim.getActor();
        txt_dienvien.setText(Html.fromHtml(htmlDienvien));
        int maphim = getResources().getIdentifier("com.hongoctuan.admin.ungdungxemphim:drawable/" + id, null, null);
        iv_phim.setImageResource(maphim);
        txt_noidungphim.setText(phim.getMovieSumary());
        list_binhluan = db.getBinhluan(id);
        final CommentCustomList binhluanAdapter = new CommentCustomList(this,R.layout.activity_binh_luan__custom_list,list_binhluan);
        lv_binhluan.setAdapter(binhluanAdapter);

        btn_xemPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = phim.getMovieUrl();
                Intent intent = new Intent(getApplicationContext(), WatchMovieBUS.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("maphim", phim);
                intent.putExtra("myData", bundle);
                startActivity(intent);
            }
        });
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertComment(phim.getMovieId(), "Nguyễn Văn A", editComment.getText().toString());
                if(list_binhluan.size()>0) {
                    CommentDTO CommentTail = new CommentDTO();
                    int positionTail = list_binhluan.size() - 1;
                    CommentTail = list_binhluan.get(positionTail);
                    list_binhluan.add(CommentTail);
                    for (int i = positionTail; i > 0; i--) {
                        list_binhluan.set(i, list_binhluan.get(i - 1));
                    }
                    CommentDTO temp = new CommentDTO();
                    temp.setCommenter("Nguyễn Văn A");
                    temp.setContent(editComment.getText().toString());
                    list_binhluan.set(0, temp);
                }else {
                    CommentDTO temp = new CommentDTO();
                    temp.setCommenter("Nguyễn Văn A");
                    temp.setContent(editComment.getText().toString());
                    list_binhluan.add(0,temp);
                }
                binhluanAdapter.notifyDataSetChanged();
            }
        });
    }
}
