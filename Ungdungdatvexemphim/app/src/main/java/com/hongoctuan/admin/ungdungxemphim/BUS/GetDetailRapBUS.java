package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.hongoctuan.admin.ungdungxemphim.View.ListLichChieu;
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

/**
 * Created by admin on 5/30/2016.
 */
public class GetDetailRapBUS extends AsyncTask<String,Void,String> {
    Activity context;
    String marap;
    public GetDetailRapBUS(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        BufferedReader in = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        URI website = null;
        try {
            // vì URI không send string chứa các " " nến convert " "  thành "_"
            String temp = params[0].replace(" ","_");
            //get thông tin rạp bằng service API
            website = new URI("http://restfullapiservice.somee.com/api/getinfomationrap?tenrap="+temp);
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
        JSONObject jsonObject = null;
        try {
            //convert dữ liệu từ kiểu string sang kiểu json.
            jsonObject = new JSONObject(s);
            TextView txt_tenrap = (TextView) context.findViewById(R.id.txt_tenrap);
            TextView txt_ngaymo = (TextView) context.findViewById(R.id.txt_ngaymo);
            TextView txt_sophong = (TextView) context.findViewById(R.id.txt_sophong);
            TextView txt_soghe = (TextView) context.findViewById(R.id.txt_soghe);
            TextView txt_sophone = (TextView) context.findViewById(R.id.txt_phonerap);
            ImageView iv_hinhrap = (ImageView) context.findViewById(R.id.iv_rap);
            //gán giá trị cho các control.
            txt_tenrap.setText("Rạp: "+jsonObject.getString("TenRap"));
            txt_ngaymo.setText("Ngày Mở: "+jsonObject.getString("NgayMo"));
            txt_sophong.setText("Phòng Chiếu: "+jsonObject.getString("SoPhongChieu"));
            txt_soghe.setText("Số Ghế: "+jsonObject.getString("SoGhe"));
            txt_sophone.setText("Hotline: " + jsonObject.getString("Phone"));
            this.marap = jsonObject.getString("id");
            //gọi hình ảnh rạp bằng thư viện picasso.
            Picasso.with(context).load(jsonObject.getString("HinhAnh")).resize(300, 300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hinhrap);
            //gọi google map truyền kinh độ và vĩ độ để convert sang kiển tọa độ cho google map.
            LatLng khtn_university = new LatLng(Double.parseDouble(jsonObject.getString("ViDo")),Double.parseDouble(jsonObject.getString("KinhDo").toString()));
            GoogleMap googleMap;
            googleMap = ((MapFragment)context.getFragmentManager().findFragmentById(R.id.map)).getMap();
            Marker khtn = googleMap.addMarker(new MarkerOptions().position(khtn_university).title("My school!").snippet("Khoa Hoc Tu Nhien!"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(khtn_university, 15));
            //bắt sự kiện click vào button xem lịch chiếu gọi hàm getlichchieu.
            Button btn_lichchieu = (Button) context.findViewById(R.id.btn_lichchieurap);
            btn_lichchieu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getlichchieu();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getlichchieu(){
        //gọi layout ListLichChieu truyền tham số là mã rạp
        Intent intent = new Intent(context, ListLichChieu.class);
        Bundle bundle = new Bundle();
        bundle.putString("marap", marap);
        intent.putExtra("myData",bundle);
        context.startActivity(intent);
    }
}
