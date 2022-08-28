package com.example.myapplication;

import com.example.myapplication.bean.HotTopic;
import com.example.myapplication.bean.HouseDetail;
import com.example.myapplication.bean.HouseList;
import com.example.myapplication.bean.Login;
import com.example.myapplication.bean.ModifyPassword;
import com.example.myapplication.bean.ModifyPersonalInfo;
import com.example.myapplication.bean.NewsList;
import com.example.myapplication.bean.NewsType;
import com.example.myapplication.bean.PersonalInfo;
import com.example.myapplication.bean.Rotation;
import com.example.myapplication.bean.Service;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("prod-api/api/rotation/list")
    Call<Rotation> getBannerResource();

    @GET("prod-api/api/service/list")
    Call<Service> getAllService();

    @GET("prod-api/press/press/list?hot=Y")
    Call<HotTopic> getHotTopic();

    @GET("prod-api/press/category/list")
    Call<NewsType> getNewsType();

    @GET("prod-api/press/press/list")
    Call<NewsList> getNewsList();

    @GET("prod-api/api/house/housing/list")
    Call<HouseList> getHouseList();

    @GET("prod-api/api/house/housing/list")
    Call<HouseList> getHouseList(@Query("houseType") String houseType);

    @GET("/prod-api/api/house/housing/{id}")
    Call<HouseDetail> getHouseDetail(@Path("id") int id);

    @POST("/prod-api/api/login")
    Call<Login> getToken(@Body RequestBody body);

    @GET("/prod-api/api/common/user/getInfo")
    Call<PersonalInfo> getPersonalInfo(@Header("Authorization") String authorization);

    @PUT("/prod-api/api/common/user")
    Call<ModifyPersonalInfo> modifyPersonalInfo(@Header("Authorization") String authorization, @Body RequestBody body);

    @PUT("/prod-api/api/common/user/resetPwd")
    Call<ModifyPassword> modifyPassword(@Header("Authorization") String authorization, @Body RequestBody body);

    @POST("/prod-api/api/common/feedback")
    Call<ModifyPassword> postFeedback(@Header("Authorization") String authorization, @Body RequestBody body);

    @FormUrlEncoded
    @POST("prod-api/api/rotation/list")
    Call<ResponseBody> postRequest(@FieldMap Map<String, String> params, @HeaderMap Map<String, String> headerMap);
}
