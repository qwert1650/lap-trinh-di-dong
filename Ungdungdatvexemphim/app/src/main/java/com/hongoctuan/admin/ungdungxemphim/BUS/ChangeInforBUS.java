package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.View.ChangeInfor;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
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
 * Created by admin on 5/20/2016.
 */
public class ChangeInforBUS extends AsyncTask<String,Void,Void> {
    Context context;
    public ChangeInforBUS(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPut httpPUT = new HttpPut("http://restfullapiservice.somee.com/api/changeinfor?username="+params[0]);
        String json = "";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmnd",params[1]);
            jsonObject.put("phone",params[2]);
            jsonObject.put("gioitinh",params[3]);
            jsonObject.put("tuoi",params[4]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        json = jsonObject.toString();

        StringEntity se = null;
        try {
            se = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPUT.setEntity(se);
        httpPUT.setHeader("Accept", "application/json");
        httpPUT.setHeader("Content-type", "application/json");
        try {
            HttpResponse httpResponse = httpclient.execute(httpPUT);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Toast.makeText(context,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
    }
}
