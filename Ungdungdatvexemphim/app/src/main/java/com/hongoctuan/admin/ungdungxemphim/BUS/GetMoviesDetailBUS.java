package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.hongoctuan.admin.ungdungxemphim.View.BookTicket;
import com.hongoctuan.admin.ungdungxemphim.View.WatchMovieBUS;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by admin on 5/24/2016.
 */
public class GetMoviesDetailBUS extends AsyncTask<String,Void,String> {
    Activity context;
    MovieDTO item;
    public GetMoviesDetailBUS(Activity context) {
        this.context = context;
    }
    @Override
    protected String doInBackground(String... params) {
        BufferedReader in = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        URI website = null;
        try {
            //gọi api lấy thông tin chi tiết phim.
            website = new URI("http://restfullapiservice.somee.com/api/getdetailmovies?id="+params[0]);
            request.setURI(website);
            HttpResponse response = null;
            response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = null;
            line = in.readLine();
            return line;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        item = new MovieDTO();
        try {
            //chuyển kiểu dữ liệu string sang json.
            JSONObject json_data = new JSONObject(s);
            //gán các giá trị vừa lấy được vào các control hiển thị.
            item.setMovieId(json_data.getString("id"));
            item.setMovieName(json_data.getString("tenphim"));
            item.setDirectorName(json_data.getString("daodien"));
            item.setActor(json_data.getString("dienvien"));
            item.setRateString(json_data.getString("danhgia"));
            item.setMovieSumary(json_data.getString("tomtat"));
            item.setCategory(json_data.getString("theloai"));
            item.setMovieUrl(json_data.getString("urlTrail"));
            item.setUrlImage(json_data.getString("urlImage"));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        ImageView iv_poster = (ImageView)context.findViewById(R.id.iv_phim);
        TextView txt_MovieName = (TextView) context.findViewById(R.id.txt_tenphim);
        TextView txt_DirectorName = (TextView) context.findViewById(R.id.txt_daodien);
        TextView txt_Actor = (TextView) context.findViewById(R.id.txt_dienvien);
        TextView txt_MovieSumary = (TextView)context.findViewById(R.id.txt_noidungphim);
        Button btn_xemPhim = (Button)context.findViewById(R.id.btn_xemphim);
        Button btn_datve2d = (Button)context.findViewById(R.id.btn_datve2d);
        Button btn_datve3d = (Button)context.findViewById(R.id.btn_datve3d);
        //lấy hình chi tiết của phim
        Picasso.with(context).load(item.getUrlImage()).resize(250, 500).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_poster);
        // gán các thông tin phim cho các control
        txt_MovieName.setText(item.getMovieName());
        txt_DirectorName.setText("Đạo Diễn: " + item.getDirectorName());
        txt_Actor.setText("Diễn Viên: "+item.getActor());
        txt_MovieSumary.setText("Tóm Tắt: "+item.getMovieSumary());
        btn_xemPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi form xem phim
                Intent intent = new Intent(context, WatchMovieBUS.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("maphim", item);
                intent.putExtra("myData", bundle);
                context.startActivity(intent);
            }
        });
        btn_datve2d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi form đặt vé với loại phim 2D theo phim nếu phim khác thể loại phim sắp chiếu với mã "hh"
                if(item.getMovieId().indexOf("hh")==-1) {
                    Intent intent = new Intent(context, BookTicket.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("maphim", item);
                    bundle.putString("kythuat", "2");
                    intent.putExtra("myData", bundle);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context,"Phim này chưa được khởi chiếu vui long đợi!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_datve3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi form đặt vé theo phim theo thể loại 3D nếu phim khác thể loại phim sắp chiếu với mã "hh"
                if(item.getMovieId().indexOf("hh")==-1) {
                    Intent intent = new Intent(context, BookTicket.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("maphim", item);
                    bundle.putString("kythuat", "3");
                    intent.putExtra("myData", bundle);
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context,"Phim này chưa được khởi chiếu vui long đợi!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
