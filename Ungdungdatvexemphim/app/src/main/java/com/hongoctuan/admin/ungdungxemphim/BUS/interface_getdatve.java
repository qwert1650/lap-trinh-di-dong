package com.hongoctuan.admin.ungdungxemphim.BUS;

import com.hongoctuan.admin.ungdungxemphim.DTO.CommentDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.DatGheDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.GioiThieuDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.HoiDapDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.LichChieuDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.RapDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.RatingDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.SumRatingDTO;
import com.hongoctuan.admin.ungdungxemphim.DTO.VeDTO;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

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
    @GET("api/gettieudehoidap")
    Call<List<HoiDapDTO>> loadtieudehoidap();
    @GET("api/getgioithieu")
    Call<GioiThieuDTO> loadgioithieu();
    @GET("api/getrating/{id}")
    Call<SumRatingDTO> loadrating(@Path("id") String tags);
    @POST("api/postrating")
    Call<RatingDTO> postrating(@Body RatingDTO rating);
    @POST("api/postbinhluan")
    Call<CommentDTO> postbinhluan(@Body CommentDTO rating);
    @GET("api/getbinhluan/{id}")
    Call<List<CommentDTO>> getbinhluan(@Path("id") String id);
    @GET("api/getshowtimes/{maphim}/{kythuat}")
    Call<List<LichChieuDTO>> getlichchieuphim(@Path("maphim") String maphim,@Path("kythuat") String kythuat);
}
