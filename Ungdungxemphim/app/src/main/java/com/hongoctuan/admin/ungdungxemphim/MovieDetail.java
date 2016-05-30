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

import com.hongoctuan.admin.ungdungxemphim.BUS.GetImageDetailBUS;
import com.hongoctuan.admin.ungdungxemphim.BUS.GetMoviesDetailBUS;
import com.hongoctuan.admin.ungdungxemphim.View.WatchMovieBUS;
import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.DTO.CommentDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;

import java.util.ArrayList;

public class MovieDetail extends Activity {
    DatabaseHelper db;

    ArrayList<CommentDTO> list_binhluan;
    ListView lv_binhluan;
    Button btnComment;
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
        list_binhluan = new ArrayList<CommentDTO>();

        lv_binhluan = (ListView) findViewById(R.id.lv_binhluan);
        btnComment = (Button) findViewById(R.id.btnComment);
        editComment = (EditText) findViewById(R.id.editComment);

        GetMoviesDetailBUS getMovieDetailBUS = new GetMoviesDetailBUS(this);
        getMovieDetailBUS.execute(id);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        Drawable progress = ratingBar.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.WHITE);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertComment(id, "Nguyễn Văn A", editComment.getText().toString());
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
                //binhluanAdapter.notifyDataSetChanged();
            }
        });
    }
}
