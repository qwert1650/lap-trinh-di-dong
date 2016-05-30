package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hongoctuan.admin.ungdungxemphim.DAO.DatabaseHelper;
import com.hongoctuan.admin.ungdungxemphim.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
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
            String temp = params[0].replace(" ","_");
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
            jsonObject = new JSONObject(s);
            TextView txt_tenrap = (TextView) context.findViewById(R.id.txt_tenrap);
            TextView txt_ngaymo = (TextView) context.findViewById(R.id.txt_ngaymo);
            TextView txt_sophong = (TextView) context.findViewById(R.id.txt_sophong);
            TextView txt_soghe = (TextView) context.findViewById(R.id.txt_soghe);
            TextView txt_sophone = (TextView) context.findViewById(R.id.txt_phonerap);
            ImageView iv_hinhrap = (ImageView) context.findViewById(R.id.iv_rap);
            txt_tenrap.setText(jsonObject.getString("TenRap"));
            txt_ngaymo.setText("Ngày Mở: "+jsonObject.getString("NgayMo"));
            txt_sophong.setText("Phòng Chiếu: "+jsonObject.getString("SoPhongChieu"));
            txt_soghe.setText("Số Ghế: "+jsonObject.getString("SoGhe"));
            txt_sophone.setText("Hotline: " + jsonObject.getString("Phone"));
            String hinh= jsonObject.getString("HinhAnh");
            GetImageDetailBUS getImageDetailBUS = new GetImageDetailBUS(context,iv_hinhrap);
            getImageDetailBUS.execute(jsonObject.getString("HinhAnh"));
            LatLng khtn_university = new LatLng(Double.parseDouble(jsonObject.getString("ViDo")),Double.parseDouble(jsonObject.getString("KinhDo").toString()));
            GoogleMap googleMap;
            googleMap = ((MapFragment)context.getFragmentManager().findFragmentById(R.id.map)).getMap();
            Marker khtn = googleMap.addMarker(new MarkerOptions().position(khtn_university).title("My school!").snippet("Khoa Hoc Tu Nhien!"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(khtn_university, 15));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
