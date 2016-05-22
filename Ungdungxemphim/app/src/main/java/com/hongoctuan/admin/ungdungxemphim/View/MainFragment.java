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

import com.hongoctuan.admin.ungdungxemphim.MovieDetail;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.hongoctuan.admin.ungdungxemphim.SlidingImageAdapter;
import com.hongoctuan.admin.ungdungxemphim.View.TypeMovie;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 5/5/2016.
 */
public class MainFragment extends Fragment implements View.OnClickListener{
    ImageView iv_hd01,iv_hd02,iv_hd03,iv_hd04,iv_hd05,iv_hh01,iv_hh02,iv_hh03,iv_hh04,iv_hh05,iv_gt01,iv_gt02,iv_gt03,iv_gt04,iv_gt05;
    TextView txt_hd01,txt_hd02,txt_hd03,txt_hd04,txt_hd05,txt_hh01,txt_hh02,txt_hh03,txt_hh04,txt_hh05,txt_gt01,txt_gt02,txt_gt03,txt_gt04,txt_gt05;
    TextView txt_hdTatca,txt_hhTatca,txt_gtTatca;
    Activity context;
    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.five};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    public MainFragment(Activity context) {
        this.context = context;
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

        iv_hh01 = (ImageView) rootView.findViewById(R.id.iv_hh01);
        iv_hh02 = (ImageView) rootView.findViewById(R.id.iv_hh02);
        iv_hh03 = (ImageView) rootView.findViewById(R.id.iv_hh03);
        iv_hh04 = (ImageView) rootView.findViewById(R.id.iv_hh04);
        iv_hh05 = (ImageView) rootView.findViewById(R.id.iv_hh05);

        iv_gt01 = (ImageView) rootView.findViewById(R.id.iv_gt01);
        iv_gt02 = (ImageView) rootView.findViewById(R.id.iv_gt02);
        iv_gt03 = (ImageView) rootView.findViewById(R.id.iv_gt03);
        iv_gt04 = (ImageView) rootView.findViewById(R.id.iv_gt04);
        iv_gt05 = (ImageView) rootView.findViewById(R.id.iv_gt05);

        txt_hd01 = (TextView) rootView.findViewById(R.id.txt_hd01);
        txt_hd02 = (TextView) rootView.findViewById(R.id.txt_hd02);
        txt_hd03 = (TextView) rootView.findViewById(R.id.txt_hd03);
        txt_hd04 = (TextView) rootView.findViewById(R.id.txt_hd04);
        txt_hd05 = (TextView) rootView.findViewById(R.id.txt_hd05);

        txt_hh01 = (TextView) rootView.findViewById(R.id.txt_hh01);
        txt_hh02 = (TextView) rootView.findViewById(R.id.txt_hh02);
        txt_hh03 = (TextView) rootView.findViewById(R.id.txt_hh03);
        txt_hh04 = (TextView) rootView.findViewById(R.id.txt_hh04);
        txt_hh05 = (TextView) rootView.findViewById(R.id.txt_hh05);

        txt_gt01 = (TextView) rootView.findViewById(R.id.txt_gt01);
        txt_gt02 = (TextView) rootView.findViewById(R.id.txt_gt02);
        txt_gt03 = (TextView) rootView.findViewById(R.id.txt_gt03);
        txt_gt04 = (TextView) rootView.findViewById(R.id.txt_gt04);
        txt_gt05 = (TextView) rootView.findViewById(R.id.txt_gt05);
        txt_hdTatca = (TextView) rootView.findViewById(R.id.txt_hdTatca);
        txt_hhTatca = (TextView) rootView.findViewById(R.id.txt_hhTatca);
        txt_gtTatca = (TextView) rootView.findViewById(R.id.txt_gtTatca);

        iv_hd01.setOnClickListener(this);
        iv_hd02.setOnClickListener(this);
        iv_hd03.setOnClickListener(this);
        iv_hd04.setOnClickListener(this);
        iv_hd05.setOnClickListener(this);

        iv_hh01.setOnClickListener(this);
        iv_hh02.setOnClickListener(this);
        iv_hh03.setOnClickListener(this);
        iv_hh04.setOnClickListener(this);
        iv_hh05.setOnClickListener(this);

        iv_gt01.setOnClickListener(this);
        iv_gt02.setOnClickListener(this);
        iv_gt03.setOnClickListener(this);
        iv_gt04.setOnClickListener(this);
        iv_gt05.setOnClickListener(this);

        txt_hdTatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAllMovie("hd");
            }
        });

        txt_hhTatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAllMovie("hh");
            }
        });

        txt_gtTatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAllMovie("gt");
            }
        });


        for(int i = 0; i < IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(context, ImagesArray));
        CirclePageIndicator indicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
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
        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });

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
        Intent intent = new Intent(context, MovieDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtra("myData",bundle);
        startActivity(intent);
    }

    public void loadAllMovie(String maloai){
        Intent intent = new Intent(context, TypeMovie.class);
        Bundle bundle = new Bundle();
        bundle.putString("maloai", maloai);
        intent.putExtra("myData",bundle);
        startActivity(intent);
    }
}
