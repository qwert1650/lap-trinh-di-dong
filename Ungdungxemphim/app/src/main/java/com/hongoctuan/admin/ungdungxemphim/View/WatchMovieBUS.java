package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.hongoctuan.admin.ungdungxemphim.RelatedMovieCustomList;

import java.util.ArrayList;

public class WatchMovieBUS extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String API_KEY = "API KEY HERE";
    private static String VIDEO_ID = "7bDLIV96LD4";
    private YouTubePlayerView videoPlayer;
    TextView txt_watchTenphim;
    ImageView iv_like,iv_unlike;
    TextView txt_like, txt_unlike;
    int trangthaiLike = 0;
    int trangthaiUnlike = 0;
    ListView lv_goiyPhim;
    DatabaseHelper db;
    ArrayList<MovieDTO> list_goiyPhim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movie);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("myData");
        txt_watchTenphim = (TextView) findViewById(R.id.txt_watchTenphim);
        iv_like = (ImageView) findViewById(R.id.iv_like);
        iv_unlike = (ImageView) findViewById(R.id.iv_unlike);
        txt_like = (TextView) findViewById(R.id.txt_like);
        txt_unlike = (TextView) findViewById(R.id.txt_unlike);
        lv_goiyPhim = (ListView) findViewById(R.id.lv_goiyPhim);
        MovieDTO phim = (MovieDTO) packageFromCaller.getSerializable("maphim");
        db = new DatabaseHelper(this);
        list_goiyPhim = db.getListPhimGoiY("hd",phim.getMovieId());
        VIDEO_ID = phim.getMovieUrl();
        videoPlayer = (YouTubePlayerView) findViewById(R.id.youtube_player);
        videoPlayer.initialize(API_KEY, this);
        txt_watchTenphim.setText(phim.getMovieName());
        iv_like.setImageResource(R.drawable.ic_like);
        iv_unlike.setImageResource(R.drawable.ic_unlike);
        iv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthaiLike == 0) {
                    iv_like.setImageResource(R.drawable.ic_clicklike);
                    int temp = Integer.parseInt(txt_like.getText().toString());
                    temp = temp + 1;
                    txt_like.setText(temp + "");
                    trangthaiLike = 1;
                } else if (trangthaiLike == 1) {
                    iv_like.setImageResource(R.drawable.ic_like);
                    int temp = Integer.parseInt(txt_like.getText().toString());
                    temp = temp - 1;
                    txt_like.setText(temp + "");
                    trangthaiLike = 0;
                }
            }
        });

        iv_unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthaiUnlike == 0) {
                    iv_unlike.setImageResource(R.drawable.ic_clickunlike);
                    int temp = Integer.parseInt(txt_unlike.getText().toString());
                    temp = temp + 1;
                    txt_unlike.setText(temp + "");
                    trangthaiUnlike = 1;
                } else if (trangthaiUnlike == 1) {
                    iv_unlike.setImageResource(R.drawable.ic_unlike);
                    int temp = Integer.parseInt(txt_unlike.getText().toString());
                    temp = temp - 1;
                    txt_unlike.setText(temp + "");
                    trangthaiUnlike = 0;
                }
            }
        });
        RelatedMovieCustomList adapter = new RelatedMovieCustomList(this,R.layout.activity_list_goi_yphim__custom,list_goiyPhim);
        lv_goiyPhim.setAdapter(adapter);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        //Toast.makeText(MainActivity.this, youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
    }
}
