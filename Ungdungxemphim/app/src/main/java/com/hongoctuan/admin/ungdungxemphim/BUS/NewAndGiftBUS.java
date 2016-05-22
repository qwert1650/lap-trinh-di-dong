package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hongoctuan.admin.ungdungxemphim.DTO.KhuyenMaiDTO;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.hongoctuan.admin.ungdungxemphim.View.ChangeInfor;
import com.hongoctuan.admin.ungdungxemphim.View.Detail_NewsAndGift;
import com.hongoctuan.admin.ungdungxemphim.View.listNewAndGift_custom;

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
 * Created by admin on 5/21/2016.
 */
public class NewAndGiftBUS extends AsyncTask<Void, Void, String> {
    Activity context;
    public NewAndGiftBUS(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        BufferedReader in = null;
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet request = new HttpGet();
        URI website = null;
        try {
            website = new URI("http://restfullapiservice.somee.com/api/getnewsandgift");
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
        super.onPostExecute(s);
        JSONArray jsonArray = null;
        final ArrayList<KhuyenMaiDTO> arrKhuyenMai = new ArrayList<KhuyenMaiDTO>();
        try {
            jsonArray = new JSONArray(s);
            JSONObject json_data = null;
            for(int i=0; i < jsonArray.length() ; i++) {
                json_data = jsonArray.getJSONObject(i);
                KhuyenMaiDTO item = new KhuyenMaiDTO();
                item.setId(Integer.parseInt(json_data.getString("Id")));
                item.setTenKM(json_data.getString("TenKM"));
                item.setNgayKM(json_data.getString("NgayKM"));
                item.setNoiDungKM(json_data.getString("NoiDungKM"));
                item.setDieuKienKM(json_data.getString("DieuKienKM"));
                item.setGhiChuKM(json_data.getString("GhiChu"));
                item.setHinhAnhKM(json_data.getString("HinhAnhKM"));
                item.setThoiHanKM(json_data.getString("ThoiHanKM"));
                arrKhuyenMai.add(item);
            }
            ListView lvNewsAndGift = (ListView) context.findViewById(R.id.lvNewsAndGift);
            listNewAndGift_custom adapter = new listNewAndGift_custom(context,R.layout.list_newandgift_custom,arrKhuyenMai);
            lvNewsAndGift.setAdapter(adapter);
            lvNewsAndGift.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, Detail_NewsAndGift.class);
                    intent.putExtra("khuyenmai", arrKhuyenMai.get(position));
                    context.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
