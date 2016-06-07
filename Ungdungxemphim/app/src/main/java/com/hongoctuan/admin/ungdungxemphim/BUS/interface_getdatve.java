package com.hongoctuan.admin.ungdungxemphim.BUS;

import com.hongoctuan.admin.ungdungxemphim.DTO.DatGheDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.VeDTO;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Url;

/**
 * Created by admin on 6/5/2016.
 */
public interface interface_getdatve {
    @GET("api/getlistdatve/{id}")
    Call<List<String>> loadQuestions(@Path("id") String tags);
    @POST("api/datve")
    Call<DatGheDTO> datghe(@Body DatGheDTO ghe);
    @GET("api/getvedadat/{id}")
    Call<List<VeDTO>> loadvedadat(@Path("id") String tags);
    @POST("api/huydatve")
    Call<DatGheDTO> huydatve(@Body  DatGheDTO ghe);
    @GET("api/getlichsudatve/{id}")
    Call<List<VeDTO>> loadlichsudatve(@Path("id") String tags);
}
