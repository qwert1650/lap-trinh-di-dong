package com.hongoctuan.admin.ungdungxemphim.BUS;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.DTO.MovieDTO;
import com.hongoctuan.admin.ungdungxemphim.R;
import com.squareup.okhttp.ResponseBody;
import com.squareup.picasso.Picasso;

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

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

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
            //gọi Api lấy danh sách phim theo mỗi thể loại được truyền vào với params là thể loại phim
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
            //chuyển danh sách phim theo thể loại từ string sang kiểu arraylist json.
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
            //nếu thể loại phim có mã "hd" là phim đang chiếu. Gọi hàm setdataActionMovie với tham số truyền vào mà arraylist phim trả về.
            if(category.equals("hd")) {
                setdataActionMovie(arrMovie);
            }
            //nếu thể loại phim có mã "gt" là phim sắp chiếu. Gọi hàm setdataFictionMovie với tham số truyền vào mà arraylist phim trả về.
            else if(category.equals("gt")){
                setdataFictionMovie(arrMovie);
            }
            //nếu thể loại phim có mã "hd" là phim bom tấn. Gọi hàm setdataCatoonMovie với tham số truyền vào mà arraylist phim trả về.
            else if(category.equals("hh")){
                setdataCatoonMovie(arrMovie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void setdataActionMovie(ArrayList<MovieDTO> arrMovie){
        //khỏi tạo các đối tượng trong danh sách phim (các đối tượng nằm ở MainFrament.
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
        //truyền hình ảnh phim cho mỗi đố tượng.
        String URL=arrMovie.get(0).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd01);
        URL=arrMovie.get(1).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd02);
        URL=arrMovie.get(2).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd03);
        URL=arrMovie.get(3).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd04);
        URL=arrMovie.get(4).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd05);
        URL=arrMovie.get(5).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd06);
        URL=arrMovie.get(6).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd07);
        URL=arrMovie.get(7).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd08);
        URL=arrMovie.get(8).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd09);
        URL=arrMovie.get(9).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hd10);
    }

    private void setdataCatoonMovie(ArrayList<MovieDTO> arrMovie){
        //khỏi tạo các đối tượng trong danh sách phim (các đối tượng nằm ở MainFrament.
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
        //truyền hình ảnh phim cho mỗi đố tượng.
        String URL=arrMovie.get(0).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh01);
        URL=arrMovie.get(1).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh02);
        URL=arrMovie.get(2).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh03);
        URL=arrMovie.get(3).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh04);
        URL=arrMovie.get(4).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh05);
        URL=arrMovie.get(5).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh06);
        URL=arrMovie.get(6).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh07);
        URL=arrMovie.get(7).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh08);
        URL=arrMovie.get(8).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh09);
        URL=arrMovie.get(9).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_hh10);
    }

    private void setdataFictionMovie(ArrayList<MovieDTO> arrMovie){
        //khỏi tạo các đối tượng trong danh sách phim (các đối tượng nằm ở MainFrament.
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

        final ImageView iv_gt01 = (ImageView) context.findViewById(R.id.iv_gt01);
        ImageView iv_gt02 = (ImageView) context.findViewById(R.id.iv_gt02);
        ImageView iv_gt03 = (ImageView) context.findViewById(R.id.iv_gt03);
        ImageView iv_gt04 = (ImageView) context.findViewById(R.id.iv_gt04);
        ImageView iv_gt05 = (ImageView) context.findViewById(R.id.iv_gt05);
        ImageView iv_gt06 = (ImageView) context.findViewById(R.id.iv_gt06);
        ImageView iv_gt07 = (ImageView) context.findViewById(R.id.iv_gt07);
        ImageView iv_gt08 = (ImageView) context.findViewById(R.id.iv_gt08);
        ImageView iv_gt09 = (ImageView) context.findViewById(R.id.iv_gt09);
        ImageView iv_gt10 = (ImageView) context.findViewById(R.id.iv_gt10);

        //truyền hình ảnh phim cho mỗi đố tượng.
        String URL=arrMovie.get(0).getUrlImage();
        Picasso.with(context).load(URL).resize(200, 300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt01);
        URL=arrMovie.get(1).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt02);
        URL=arrMovie.get(2).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt03);
        URL=arrMovie.get(3).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt04);
        URL=arrMovie.get(4).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt05);
        URL=arrMovie.get(5).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt06);
        URL=arrMovie.get(6).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt07);
        URL=arrMovie.get(7).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt08);
        URL=arrMovie.get(8).getUrlImage();
        Picasso.with(context).load(URL).resize(200,300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt09);
        URL=arrMovie.get(9).getUrlImage();
        Picasso.with(context).load(URL).resize(200, 300).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(iv_gt10);
    }
}
