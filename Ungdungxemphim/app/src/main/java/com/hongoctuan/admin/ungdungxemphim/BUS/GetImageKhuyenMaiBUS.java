package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.R;

import java.io.InputStream;

/**
 * Created by admin on 5/22/2016.
 */
public class GetImageKhuyenMaiBUS extends AsyncTask<String,Void,Bitmap>{
    Activity context;
    LinearLayout llHinhAnhKM;
    public GetImageKhuyenMaiBUS(Activity context, LinearLayout llHinhAnhKM) {
        this.context = context;
        this.llHinhAnhKM = llHinhAnhKM;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        Drawable drawableBitmap = new BitmapDrawable(context.getResources(), result);
        llHinhAnhKM.setBackground(drawableBitmap);
    }
}
