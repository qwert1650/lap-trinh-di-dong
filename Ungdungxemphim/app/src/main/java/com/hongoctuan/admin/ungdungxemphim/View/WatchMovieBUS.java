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
        MovieDTO phim = (MovieDTO) packageFromCaller.getSerializable("maphim");
        VIDEO_ID = phim.getMovieUrl();
        videoPlayer = (YouTubePlayerView) findViewById(R.id.youtube_player);
        videoPlayer.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
    }
}
