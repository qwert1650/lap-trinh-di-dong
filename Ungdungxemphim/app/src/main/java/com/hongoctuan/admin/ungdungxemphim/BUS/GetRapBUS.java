package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.RapDTO;
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
import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by admin on 5/26/2016.
 */
public class GetRapBUS extends AsyncTask<String,Void,String> {
    SharedPreferences sharedPreferences;
    Activity context;
    public GetRapBUS(Activity context) {
        this.context = context;
    }



    @Override
    protected String doInBackground(String... params) {
        BufferedReader in = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        URI website = null;
        try {
            website = new URI("http://restfullapiservice.somee.com/api/getrap?maphim="+params[0]);
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
        JSONArray jsonArray = null;
        final ArrayList<RapDTO> arrRap = new ArrayList<RapDTO>();
        final ArrayList<String> arrstringRap = new ArrayList<String>();
        try {
            jsonArray = new JSONArray(s);
            JSONObject json_data = null;
            for(int i=0; i < jsonArray.length() ; i++) {
                json_data = jsonArray.getJSONObject(i);
                RapDTO item = new RapDTO();
                item.setId(json_data.getString("id"));
                item.setNameRap(json_data.getString("TenRap"));
                arrRap.add(item);
                arrstringRap.add(item.getNameRap());
            }
//            sharedPreferences = context.getSharedPreferences("datvexemphim", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor= sharedPreferences.edit();
//            editor.putString("marap", arrRap.get(0).getId());
//            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Spinner spin=(Spinner) context.findViewById(R.id.sp_rap);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,arrstringRap);
//        spin.setAdapter(adapter);
    }
}
