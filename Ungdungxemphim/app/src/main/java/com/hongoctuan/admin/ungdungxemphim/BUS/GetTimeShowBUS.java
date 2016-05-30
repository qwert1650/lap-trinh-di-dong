package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hongoctuan.admin.ungdungxemphim.DTO.LichChieuDTO;
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
import java.util.ArrayList;

/**
 * Created by admin on 5/26/2016.
 */
public class GetTimeShowBUS extends AsyncTask<String,Void,String> {
    Activity context;
    public GetTimeShowBUS(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader in = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        URI website = null;
        try {
            website = new URI("http://restfullapiservice.somee.com/api/getshowtimes?maphim="+params[0]+"&marap="+params[1]+"&kythuat="+params[2]);
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
        final ArrayList<LichChieuDTO> arrLichChieu = new ArrayList<LichChieuDTO>();
        final ArrayList<String> arrstringLichChieu = new ArrayList<String>();
        try {
            jsonArray = new JSONArray(s);
            JSONObject json_data = null;
            for(int i=0; i < jsonArray.length() ; i++) {
                json_data = jsonArray.getJSONObject(i);
                LichChieuDTO item = new LichChieuDTO();
                item.setId(Integer.parseInt(json_data.getString("Id")));
                item.setMaPhim(json_data.getString("MaPhim"));
                item.setThoiGianChieu(json_data.getString("ThoiGianChieu"));
                item.setMaPhong(Integer.parseInt(json_data.getString("MaPhong")));
                item.setMaRap(Integer.parseInt(json_data.getString("MaRap")));
                arrLichChieu.add(item);
                arrstringLichChieu.add(item.getThoiGianChieu()+"-"+"Phòng " + item.getMaPhong());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView lv_lichchieu2D=(ListView) context.findViewById(R.id.list_lichchieu2d);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,arrstringLichChieu);
        lv_lichchieu2D.setAdapter(adapter);
    }
}
