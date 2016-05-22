package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by admin on 5/11/2016.
 */
public class ChangePasswordBUS extends AsyncTask<String,Void,Void> {
    @Override
    protected Void doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPut httpPUT = new HttpPut("http://restfullapiservice.somee.com/api/changepass?username="+params[0]);
        String json = "";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pass",params[1]);
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
}
