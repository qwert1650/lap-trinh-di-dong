package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.os.AsyncTask;

import com.hongoctuan.admin.ungdungxemphim.DTO.AccountDTO;
import com.hongoctuan.admin.ungdungxemphim.View.LoginLayout;

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
 * Created by admin on 5/1/2016.
 */
public class RegisterAccountBUS extends AsyncTask<AccountDTO, Void, String> {

    AccountDTO user = new AccountDTO();
    Activity context;
    public RegisterAccountBUS(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(AccountDTO... params) {
        user = params[0];
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://restfullapiservice.somee.com/api/insertaccount/");
        httpPost.setHeader("content-type", "application/json");
        JSONObject data = new JSONObject();
        try {
            data.put("name",user.getAccountName().toString());
            data.put("pass",user.getPassword().toString());
            data.put("cmnd",user.getPassword().toString());
            data.put("phone",user.getPhoneNumber().toString());
            data.put("gioitinh",user.getSex().toString());
            data.put("tuoi",user.getAge().toString());
            StringEntity entity = null;
            entity = new StringEntity(data.toString(), HTTP.UTF_8);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return bufferedReader.readLine();
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
        super.onPostExecute(s);
        LoginLayout loginlayout = new LoginLayout(context);
        loginlayout.updateLogginSuccesLayout(user);
    }
}
