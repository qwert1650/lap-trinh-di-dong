package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.BUS.GetMoviesCatogeryBUS;
import com.hongoctuan.admin.ungdungxemphim.MovieDetail;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.hongoctuan.admin.ungdungxemphim.SlidingImageAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 5/5/2016.
 */
public class MainFragment extends Fragment implements View.OnClickListener{
    ImageView iv_hd01,iv_hd02,iv_hd03,iv_hd04,iv_hd05,iv_hh01,iv_hh02,iv_hh03,iv_hh04,iv_hh05,iv_gt01,iv_gt02,iv_gt03,iv_gt04,iv_gt05;
    TextView txt_hd01,txt_hd02,txt_hd03,txt_hd04,txt_hd05,txt_hh01,txt_hh02,txt_hh03,txt_hh04,txt_hh05,txt_gt01,txt_gt02,txt_gt03,txt_gt04,txt_gt05;

    ImageView iv_hd06,iv_hd07,iv_hd08,iv_hd09,iv_hd10,iv_hh06,iv_hh07,iv_hh08,iv_hh09,iv_hh10,iv_gt06,iv_gt07,iv_gt08,iv_gt09,iv_gt10;
    TextView txt_hd06,txt_hd07,txt_hd08,txt_hd09,txt_hd10,txt_hh06,txt_hh07,txt_hh08,txt_hh09,txt_hh10,txt_gt06,txt_gt07,txt_gt08,txt_gt09,txt_gt10;

    TextView txt_hdTatca,txt_hhTatca,txt_gtTatca;
    Activity context;
    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.five};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    public MainFragment(Activity context) {
        this.context = context;
        GetMoviesCatogeryBUS getMoviesActionBUS = new GetMoviesCatogeryBUS(context);
        getMoviesActionBUS.execute("hd");
        GetMoviesCatogeryBUS getMoviesCatoonBUS = new GetMoviesCatogeryBUS(context);
        getMoviesCatoonBUS.execute("hh");
        GetMoviesCatogeryBUS getMoviesFictionBUS = new GetMoviesCatogeryBUS(context);
        getMoviesFictionBUS.execute("gt");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragmen, container, false);
        iv_hd01 = (ImageView) rootView.findViewById(R.id.iv_hd01);
        iv_hd02 = (ImageView) rootView.findViewById(R.id.iv_hd02);
        iv_hd03 = (ImageView) rootView.findViewById(R.id.iv_hd03);
        iv_hd04 = (ImageView) rootView.findViewById(R.id.iv_hd04);
        iv_hd05 = (ImageView) rootView.findViewById(R.id.iv_hd05);
        iv_hd06 = (ImageView) rootView.findViewById(R.id.iv_hd06);
        iv_hd07 = (ImageView) rootView.findViewById(R.id.iv_hd07);
        iv_hd08 = (ImageView) rootView.findViewById(R.id.iv_hd08);
        iv_hd09 = (ImageView) rootView.findViewById(R.id.iv_hd09);
        iv_hd10 = (ImageView) rootView.findViewById(R.id.iv_hd10);

        iv_hh01 = (ImageView) rootView.findViewById(R.id.iv_hh01);
        iv_hh02 = (ImageView) rootView.findViewById(R.id.iv_hh02);
        iv_hh03 = (ImageView) rootView.findViewById(R.id.iv_hh03);
        iv_hh04 = (ImageView) rootView.findViewById(R.id.iv_hh04);
        iv_hh05 = (ImageView) rootView.findViewById(R.id.iv_hh05);
        iv_hh06 = (ImageView) rootView.findViewById(R.id.iv_hh06);
        iv_hh07 = (ImageView) rootView.findViewById(R.id.iv_hh07);
        iv_hh08 = (ImageView) rootView.findViewById(R.id.iv_hh08);
        iv_hh09 = (ImageView) rootView.findViewById(R.id.iv_hh09);
        iv_hh10 = (ImageView) rootView.findViewById(R.id.iv_hh10);

        iv_gt01 = (ImageView) rootView.findViewById(R.id.iv_gt01);
        iv_gt02 = (ImageView) rootView.findViewById(R.id.iv_gt02);
        iv_gt03 = (ImageView) rootView.findViewById(R.id.iv_gt03);
        iv_gt04 = (ImageView) rootView.findViewById(R.id.iv_gt04);
        iv_gt05 = (ImageView) rootView.findViewById(R.id.iv_gt05);
        iv_gt06 = (ImageView) rootView.findViewById(R.id.iv_gt06);
        iv_gt07 = (ImageView) rootView.findViewById(R.id.iv_gt07);
        iv_gt08 = (ImageView) rootView.findViewById(R.id.iv_gt08);
        iv_gt09 = (ImageView) rootView.findViewById(R.id.iv_gt09);
        iv_gt10 = (ImageView) rootView.findViewById(R.id.iv_gt10);

        txt_hd01 = (TextView) rootView.findViewById(R.id.txt_hd01);
        txt_hd02 = (TextView) rootView.findViewById(R.id.txt_hd02);
        txt_hd03 = (TextView) rootView.findViewById(R.id.txt_hd03);
        txt_hd04 = (TextView) rootView.findViewById(R.id.txt_hd04);
        txt_hd05 = (TextView) rootView.findViewById(R.id.txt_hd05);
        txt_hd06 = (TextView) rootView.findViewById(R.id.txt_hd06);
        txt_hd07 = (TextView) rootView.findViewById(R.id.txt_hd07);
        txt_hd08 = (TextView) rootView.findViewById(R.id.txt_hd08);
        txt_hd09 = (TextView) rootView.findViewById(R.id.txt_hd09);
        txt_hd10 = (TextView) rootView.findViewById(R.id.txt_hd10);

        txt_hh01 = (TextView) rootView.findViewById(R.id.txt_hh01);
        txt_hh02 = (TextView) rootView.findViewById(R.id.txt_hh02);
        txt_hh03 = (TextView) rootView.findViewById(R.id.txt_hh03);
        txt_hh04 = (TextView) rootView.findViewById(R.id.txt_hh04);
        txt_hh05 = (TextView) rootView.findViewById(R.id.txt_hh05);
        txt_hh06 = (TextView) rootView.findViewById(R.id.txt_hh06);
        txt_hh07 = (TextView) rootView.findViewById(R.id.txt_hh07);
        txt_hh08= (TextView) rootView.findViewById(R.id.txt_hh08);
        txt_hh09 = (TextView) rootView.findViewById(R.id.txt_hh09);
        txt_hh10 = (TextView) rootView.findViewById(R.id.txt_hh10);

        txt_gt01 = (TextView) rootView.findViewById(R.id.txt_gt01);
        txt_gt02 = (TextView) rootView.findViewById(R.id.txt_gt02);
        txt_gt03 = (TextView) rootView.findViewById(R.id.txt_gt03);
        txt_gt04 = (TextView) rootView.findViewById(R.id.txt_gt04);
        txt_gt05 = (TextView) rootView.findViewById(R.id.txt_gt05);
        txt_gt06 = (TextView) rootView.findViewById(R.id.txt_gt06);
        txt_gt07 = (TextView) rootView.findViewById(R.id.txt_gt07);
        txt_gt08 = (TextView) rootView.findViewById(R.id.txt_gt08);
        txt_gt09 = (TextView) rootView.findViewById(R.id.txt_gt09);
        txt_gt10 = (TextView) rootView.findViewById(R.id.txt_gt10);

        iv_hd01.setOnClickListener(this);
        iv_hd02.setOnClickListener(this);
        iv_hd03.setOnClickListener(this);
        iv_hd04.setOnClickListener(this);
        iv_hd05.setOnClickListener(this);
        iv_hd06.setOnClickListener(this);
        iv_hd07.setOnClickListener(this);
        iv_hd08.setOnClickListener(this);
        iv_hd09.setOnClickListener(this);
        iv_hd10.setOnClickListener(this);

        iv_hh01.setOnClickListener(this);
        iv_hh02.setOnClickListener(this);
        iv_hh03.setOnClickListener(this);
        iv_hh04.setOnClickListener(this);
        iv_hh05.setOnClickListener(this);
        iv_hh06.setOnClickListener(this);
        iv_hh07.setOnClickListener(this);
        iv_hh08.setOnClickListener(this);
        iv_hh09.setOnClickListener(this);
        iv_hh10.setOnClickListener(this);

        iv_gt01.setOnClickListener(this);
        iv_gt02.setOnClickListener(this);
        iv_gt03.setOnClickListener(this);
        iv_gt04.setOnClickListener(this);
        iv_gt05.setOnClickListener(this);
        iv_gt06.setOnClickListener(this);
        iv_gt07.setOnClickListener(this);
        iv_gt08.setOnClickListener(this);
        iv_gt09.setOnClickListener(this);
        iv_gt10.setOnClickListener(this);

        for(int i = 0; i < IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(context, ImagesArray));
        final float density = getResources().getDisplayMetrics().density;
        NUM_PAGES =IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        String id="";
        if(v.getId() == iv_hd01.getId())
            id = "hd01";
        else if(v.getId() == iv_hd02.getId())
            id = "hd02";
        else if(v.getId() == iv_hd03.getId())
            id = "hd03";
        else if(v.getId() == iv_hd04.getId())
            id = "hd04";
        else if(v.getId() == iv_hd05.getId())
            id = "hd05";
        else if(v.getId() == iv_hh01.getId())
            id = "hh01";
        else if(v.getId() == iv_hh02.getId())
            id = "hh02";
        else if(v.getId() == iv_hh03.getId())
            id = "hh03";
        else if(v.getId() == iv_hh04.getId())
            id = "hh04";
        else if(v.getId() == iv_hh05.getId())
            id = "hh05";
        else if(v.getId() == iv_gt01.getId())
            id = "gt01";
        else if(v.getId() == iv_gt02.getId())
            id = "gt02";
        else if(v.getId() == iv_gt03.getId())
            id = "gt03";
        else if(v.getId() == iv_gt04.getId())
            id = "gt04";
        else if(v.getId() == iv_gt05.getId())
            id = "gt05";
        else if(v.getId() == iv_hd06.getId())
            id = "hd06";
        else if(v.getId() == iv_hd07.getId())
            id = "hd07";
        else if(v.getId() == iv_hd08.getId())
            id = "hd08";
        else if(v.getId() == iv_hd09.getId())
            id = "hd09";
        else if(v.getId() == iv_hd10.getId())
            id = "hd10";
        else if(v.getId() == iv_hh06.getId())
            id = "hh06";
        else if(v.getId() == iv_hh07.getId())
            id = "hh07";
        else if(v.getId() == iv_hh08.getId())
            id = "hh08";
        else if(v.getId() == iv_hh09.getId())
            id = "hh09";
        else if(v.getId() == iv_hh10.getId())
            id = "hh10";
        else if(v.getId() == iv_gt06.getId())
            id = "gt06";
        else if(v.getId() == iv_gt07.getId())
            id = "gt07";
        else if(v.getId() == iv_gt08.getId())
            id = "gt08";
        else if(v.getId() == iv_gt09.getId())
            id = "gt09";
        else if(v.getId() == iv_gt10.getId())
            id = "gt10";
        Intent intent = new Intent(context, MovieDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtra("myData",bundle);
        startActivity(intent);
    }
}
