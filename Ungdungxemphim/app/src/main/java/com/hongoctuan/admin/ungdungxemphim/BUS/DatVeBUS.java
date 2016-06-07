package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.DTO.DatGheDTO;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by admin on 6/5/2016.
 */
public class DatVeBUS extends AsyncTask<Void,Void,String> {
    Context context;
    DatGheDTO object;
    public DatVeBUS(Context context, DatGheDTO object) {
        this.context = context;
        this.object = object;
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://restfullapiservice.somee.com/api/datve");
        httpPost.setHeader("content-type", "application/json");
        JSONObject data = new JSONObject();
        try {
            data.put("malichchieu",object.getMalichchieu());
            data.put("maghe",object.getMaghe());
            data.put("trangthai",1);
            data.put("taikhoan",object.getTaikhoan());
            StringEntity entity = null;
            entity = new StringEntity(data.toString(), HTTP.UTF_8);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context,"Đặt vé thành công",Toast.LENGTH_SHORT).show();
    }
}
