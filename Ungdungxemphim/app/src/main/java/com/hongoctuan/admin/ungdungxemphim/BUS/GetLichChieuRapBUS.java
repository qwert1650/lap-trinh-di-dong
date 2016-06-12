package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ExpandableListView;

import com.hongoctuan.admin.ungdungxemphim.DTO.LichChieuRapDTO;
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
import java.util.HashSet;

/**
 * Created by admin on 6/1/2016.
 */
public class GetLichChieuRapBUS extends AsyncTask<String,Void,String> {
    Activity context;
    public GetLichChieuRapBUS(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader in = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        URI website = null;
        try {
            website = new URI("http://restfullapiservice.somee.com/api/getlichchieurap?marap=" + params[0]);
            request.setURI(website);
            HttpResponse response = null;
            response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return in.readLine();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        final ArrayList<String> listtenphim = new ArrayList<String>();
        final ArrayList<LichChieuRapDTO> listlichchieu = new ArrayList<LichChieuRapDTO>();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(s);
            JSONObject json_data = null;
            for(int i=0; i < jsonArray.length() ; i++) {
                json_data = jsonArray.getJSONObject(i);
                LichChieuRapDTO item = new LichChieuRapDTO();
                item.setTenphim(json_data.getString("tenphim"));
                item.setKythuat(json_data.getString("kythuat"));
                item.setThoigianchieu(json_data.getString("thoigianchieu"));
                item.setMarap(json_data.getString("marap"));
                item.setMalichchieu(json_data.getString("malichchieu"));
                item.setGiave(json_data.getString("giave"));
                listlichchieu.add(item);
                listtenphim.add(json_data.getString("tenphim"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> unique = removeDuplicates(listtenphim);
        final String[] item = new String[unique.size()];
        for(int i =0 ;i < unique.size(); i++) {
            item[i] = unique.get(i);
        }
        ExpandableListView listView = (ExpandableListView)context.findViewById(R.id.expandableListView1);

        listView.setGroupIndicator(null);

        ExpandAdapter_LichChieu adapter = new ExpandAdapter_LichChieu(context, item,listlichchieu);

        listView.setAdapter(adapter);
    }
    static ArrayList<String> removeDuplicates(ArrayList<String> list) {

        // Store unique items in result.
        ArrayList<String> result = new ArrayList<>();

        // Record encountered Strings in HashSet.
        HashSet<String> set = new HashSet<>();

        // Loop over argument list.
        for (String item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }
}
