package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
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
 * Created by admin on 5/24/2016.
 */
public class GetMoviesCatogeryBUS extends AsyncTask<String,Void,String> {
    Activity context;
    String category;
    public GetMoviesCatogeryBUS(Activity context) {
        this.context = context;
    }
    @Override
    protected String doInBackground(String... params) {
        BufferedReader in = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        category = params[0];
        URI website = null;
        try {
            website = new URI("http://restfullapiservice.somee.com/api/getcategorymovies?theloai="+params[0]);
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
        final ArrayList<MovieDTO> arrMovie = new ArrayList<MovieDTO>();
        try {
            jsonArray = new JSONArray(s);
            JSONObject json_data = null;
            for(int i=0; i < jsonArray.length() ; i++) {
                json_data = jsonArray.getJSONObject(i);
                MovieDTO item = new MovieDTO();
                item.setMovieId(json_data.getString("id"));
                item.setMovieName(json_data.getString("tenphim"));
                item.setDirectorName(json_data.getString("daodien"));
                item.setActor(json_data.getString("dienvien"));
                item.setRateString(json_data.getString("danhgia"));
                item.setMovieSumary(json_data.getString("tomtat"));
                item.setCategory(json_data.getString("theloai"));
                item.setMovieUrl(json_data.getString("urlTrail"));
                item.setUrlImage(json_data.getString("urlImage"));
                arrMovie.add(item);
            }
            if(category.equals("hd")) {
                setdataActionMovie(arrMovie);
            }else if(category.equals("gt")){
                setdataFictionMovie(arrMovie);
            }else if(category.equals("hh")){
                setdataCatoonMovie(arrMovie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void setdataActionMovie(ArrayList<MovieDTO> arrMovie){
        TextView txt_hd01 = (TextView) context.findViewById(R.id.txt_hd01);
        TextView txt_hd02 = (TextView) context.findViewById(R.id.txt_hd02);
        TextView txt_hd03 = (TextView) context.findViewById(R.id.txt_hd03);
        TextView txt_hd04 = (TextView) context.findViewById(R.id.txt_hd04);
        TextView txt_hd05 = (TextView) context.findViewById(R.id.txt_hd05);
        TextView txt_hd06 = (TextView) context.findViewById(R.id.txt_hd06);
        TextView txt_hd07 = (TextView) context.findViewById(R.id.txt_hd07);
        TextView txt_hd08 = (TextView) context.findViewById(R.id.txt_hd08);
        TextView txt_hd09 = (TextView) context.findViewById(R.id.txt_hd09);
        TextView txt_hd10 = (TextView) context.findViewById(R.id.txt_hd10);

        txt_hd01.setText(arrMovie.get(0).getMovieName());
        txt_hd02.setText(arrMovie.get(1).getMovieName());
        txt_hd03.setText(arrMovie.get(2).getMovieName());
        txt_hd04.setText(arrMovie.get(3).getMovieName());
        txt_hd05.setText(arrMovie.get(4).getMovieName());
        txt_hd06.setText(arrMovie.get(5).getMovieName());
        txt_hd07.setText(arrMovie.get(6).getMovieName());
        txt_hd08.setText(arrMovie.get(7).getMovieName());
        txt_hd09.setText(arrMovie.get(8).getMovieName());
        txt_hd10.setText(arrMovie.get(9).getMovieName());

        ImageView iv_hd01 = (ImageView) context.findViewById(R.id.iv_hd01);
        ImageView iv_hd02 = (ImageView) context.findViewById(R.id.iv_hd02);
        ImageView iv_hd03 = (ImageView) context.findViewById(R.id.iv_hd03);
        ImageView iv_hd04 = (ImageView) context.findViewById(R.id.iv_hd04);
        ImageView iv_hd05 = (ImageView) context.findViewById(R.id.iv_hd05);
        ImageView iv_hd06 = (ImageView) context.findViewById(R.id.iv_hd06);
        ImageView iv_hd07 = (ImageView) context.findViewById(R.id.iv_hd07);
        ImageView iv_hd08 = (ImageView) context.findViewById(R.id.iv_hd08);
        ImageView iv_hd09 = (ImageView) context.findViewById(R.id.iv_hd09);
        ImageView iv_hd10 = (ImageView) context.findViewById(R.id.iv_hd10);

        GetImageDetailBUS getHd01BUS = new GetImageDetailBUS(context,iv_hd01);
        getHd01BUS.execute(arrMovie.get(0).getUrlImage());
        GetImageDetailBUS getHd02BUS = new GetImageDetailBUS(context,iv_hd02);
        getHd02BUS.execute(arrMovie.get(1).getUrlImage());
        GetImageDetailBUS getHd03BUS = new GetImageDetailBUS(context,iv_hd03);
        getHd03BUS.execute(arrMovie.get(2).getUrlImage());
        GetImageDetailBUS getHd04BUS = new GetImageDetailBUS(context,iv_hd04);
        getHd04BUS.execute(arrMovie.get(3).getUrlImage());
        GetImageDetailBUS getHd05BUS = new GetImageDetailBUS(context,iv_hd05);
        getHd05BUS.execute(arrMovie.get(4).getUrlImage());
        GetImageDetailBUS getHd06BUS = new GetImageDetailBUS(context,iv_hd06);
        getHd06BUS.execute(arrMovie.get(5).getUrlImage());
        GetImageDetailBUS getHd07BUS = new GetImageDetailBUS(context,iv_hd07);
        getHd07BUS.execute(arrMovie.get(6).getUrlImage());
        GetImageDetailBUS getHd08BUS = new GetImageDetailBUS(context,iv_hd08);
        getHd08BUS.execute(arrMovie.get(7).getUrlImage());
        GetImageDetailBUS getHd09BUS = new GetImageDetailBUS(context,iv_hd09);
        getHd09BUS.execute(arrMovie.get(8).getUrlImage());
        GetImageDetailBUS getHd10BUS = new GetImageDetailBUS(context,iv_hd10);
        getHd10BUS.execute(arrMovie.get(9).getUrlImage());
    }

    private void setdataCatoonMovie(ArrayList<MovieDTO> arrMovie){
        TextView txt_hh01 = (TextView) context.findViewById(R.id.txt_hh01);
        TextView txt_hh02 = (TextView) context.findViewById(R.id.txt_hh02);
        TextView txt_hh03 = (TextView) context.findViewById(R.id.txt_hh03);
        TextView txt_hh04 = (TextView) context.findViewById(R.id.txt_hh04);
        TextView txt_hh05 = (TextView) context.findViewById(R.id.txt_hh05);
        TextView txt_hh06 = (TextView) context.findViewById(R.id.txt_hh06);
        TextView txt_hh07 = (TextView) context.findViewById(R.id.txt_hh07);
        TextView txt_hh08 = (TextView) context.findViewById(R.id.txt_hh08);
        TextView txt_hh09 = (TextView) context.findViewById(R.id.txt_hh09);
        TextView txt_hh10 = (TextView) context.findViewById(R.id.txt_hh10);

        txt_hh01.setText(arrMovie.get(0).getMovieName());
        txt_hh02.setText(arrMovie.get(1).getMovieName());
        txt_hh03.setText(arrMovie.get(2).getMovieName());
        txt_hh04.setText(arrMovie.get(3).getMovieName());
        txt_hh05.setText(arrMovie.get(4).getMovieName());
        txt_hh06.setText(arrMovie.get(5).getMovieName());
        txt_hh07.setText(arrMovie.get(6).getMovieName());
        txt_hh08.setText(arrMovie.get(7).getMovieName());
        txt_hh09.setText(arrMovie.get(8).getMovieName());
        txt_hh10.setText(arrMovie.get(9).getMovieName());

        ImageView iv_hh01 = (ImageView) context.findViewById(R.id.iv_hh01);
        ImageView iv_hh02 = (ImageView) context.findViewById(R.id.iv_hh02);
        ImageView iv_hh03 = (ImageView) context.findViewById(R.id.iv_hh03);
        ImageView iv_hh04 = (ImageView) context.findViewById(R.id.iv_hh04);
        ImageView iv_hh05 = (ImageView) context.findViewById(R.id.iv_hh05);
        ImageView iv_hh06 = (ImageView) context.findViewById(R.id.iv_hh06);
        ImageView iv_hh07 = (ImageView) context.findViewById(R.id.iv_hh07);
        ImageView iv_hh08 = (ImageView) context.findViewById(R.id.iv_hh08);
        ImageView iv_hh09 = (ImageView) context.findViewById(R.id.iv_hh09);
        ImageView iv_hh10 = (ImageView) context.findViewById(R.id.iv_hh10);

        GetImageDetailBUS getHh01BUS = new GetImageDetailBUS(context,iv_hh01);
        getHh01BUS.execute(arrMovie.get(0).getUrlImage());
        GetImageDetailBUS getHh02BUS = new GetImageDetailBUS(context,iv_hh02);
        getHh02BUS.execute(arrMovie.get(1).getUrlImage());
        GetImageDetailBUS getHh03BUS = new GetImageDetailBUS(context,iv_hh03);
        getHh03BUS.execute(arrMovie.get(2).getUrlImage());
        GetImageDetailBUS getHh04BUS = new GetImageDetailBUS(context,iv_hh04);
        getHh04BUS.execute(arrMovie.get(3).getUrlImage());
        GetImageDetailBUS getHh05BUS = new GetImageDetailBUS(context,iv_hh05);
        getHh05BUS.execute(arrMovie.get(4).getUrlImage());
        GetImageDetailBUS getHh06BUS = new GetImageDetailBUS(context,iv_hh06);
        getHh06BUS.execute(arrMovie.get(5).getUrlImage());
        GetImageDetailBUS getHh07BUS = new GetImageDetailBUS(context,iv_hh07);
        getHh07BUS.execute(arrMovie.get(6).getUrlImage());
        GetImageDetailBUS getHh08BUS = new GetImageDetailBUS(context,iv_hh08);
        getHh08BUS.execute(arrMovie.get(7).getUrlImage());
        GetImageDetailBUS getHh09BUS = new GetImageDetailBUS(context,iv_hh09);
        getHh09BUS.execute(arrMovie.get(8).getUrlImage());
        GetImageDetailBUS getHh10BUS = new GetImageDetailBUS(context,iv_hh10);
        getHh10BUS.execute(arrMovie.get(9).getUrlImage());
    }

    private void setdataFictionMovie(ArrayList<MovieDTO> arrMovie){
        TextView txt_gt01 = (TextView) context.findViewById(R.id.txt_gt01);
        TextView txt_gt02 = (TextView) context.findViewById(R.id.txt_gt02);
        TextView txt_gt03 = (TextView) context.findViewById(R.id.txt_gt03);
        TextView txt_gt04 = (TextView) context.findViewById(R.id.txt_gt04);
        TextView txt_gt05 = (TextView) context.findViewById(R.id.txt_gt05);
        TextView txt_gt06 = (TextView) context.findViewById(R.id.txt_gt06);
        TextView txt_gt07 = (TextView) context.findViewById(R.id.txt_gt07);
        TextView txt_gt08 = (TextView) context.findViewById(R.id.txt_gt08);
        TextView txt_gt09 = (TextView) context.findViewById(R.id.txt_gt09);
        TextView txt_gt10 = (TextView) context.findViewById(R.id.txt_gt10);

        txt_gt01.setText(arrMovie.get(0).getMovieName());
        txt_gt02.setText(arrMovie.get(1).getMovieName());
        txt_gt03.setText(arrMovie.get(2).getMovieName());
        txt_gt04.setText(arrMovie.get(3).getMovieName());
        txt_gt05.setText(arrMovie.get(4).getMovieName());
        txt_gt06.setText(arrMovie.get(5).getMovieName());
        txt_gt07.setText(arrMovie.get(6).getMovieName());
        txt_gt08.setText(arrMovie.get(7).getMovieName());
        txt_gt09.setText(arrMovie.get(8).getMovieName());
        txt_gt10.setText(arrMovie.get(9).getMovieName());

        ImageView iv_gt01 = (ImageView) context.findViewById(R.id.iv_gt01);
        ImageView iv_gt02 = (ImageView) context.findViewById(R.id.iv_gt02);
        ImageView iv_gt03 = (ImageView) context.findViewById(R.id.iv_gt03);
        ImageView iv_gt04 = (ImageView) context.findViewById(R.id.iv_gt04);
        ImageView iv_gt05 = (ImageView) context.findViewById(R.id.iv_gt05);
        ImageView iv_gt06 = (ImageView) context.findViewById(R.id.iv_gt06);
        ImageView iv_gt07 = (ImageView) context.findViewById(R.id.iv_gt07);
        ImageView iv_gt08 = (ImageView) context.findViewById(R.id.iv_gt08);
        ImageView iv_gt09 = (ImageView) context.findViewById(R.id.iv_gt09);
        ImageView iv_gt10 = (ImageView) context.findViewById(R.id.iv_gt10);

        GetImageDetailBUS getGt01BUS = new GetImageDetailBUS(context,iv_gt01);
        getGt01BUS.execute(arrMovie.get(0).getUrlImage());
        GetImageDetailBUS getGt02BUS = new GetImageDetailBUS(context,iv_gt02);
        getGt02BUS.execute(arrMovie.get(1).getUrlImage());
        GetImageDetailBUS getGt03BUS = new GetImageDetailBUS(context,iv_gt03);
        getGt03BUS.execute(arrMovie.get(2).getUrlImage());
        GetImageDetailBUS getGt04BUS = new GetImageDetailBUS(context,iv_gt04);
        getGt04BUS.execute(arrMovie.get(3).getUrlImage());
        GetImageDetailBUS getGt05BUS = new GetImageDetailBUS(context,iv_gt05);
        getGt05BUS.execute(arrMovie.get(4).getUrlImage());
        GetImageDetailBUS getGt06BUS = new GetImageDetailBUS(context,iv_gt06);
        getGt06BUS.execute(arrMovie.get(5).getUrlImage());
        GetImageDetailBUS getGt07BUS = new GetImageDetailBUS(context,iv_gt07);
        getGt07BUS.execute(arrMovie.get(6).getUrlImage());
        GetImageDetailBUS getGt08BUS = new GetImageDetailBUS(context,iv_gt08);
        getGt08BUS.execute(arrMovie.get(7).getUrlImage());
        GetImageDetailBUS getGt09BUS = new GetImageDetailBUS(context,iv_gt09);
        getGt09BUS.execute(arrMovie.get(8).getUrlImage());
        GetImageDetailBUS getGt10BUS = new GetImageDetailBUS(context,iv_gt10);
        getGt10BUS.execute(arrMovie.get(9).getUrlImage());
    }
}
