package com.sumit.restapi.Retrofit;

import com.sumit.restapi.DataModel.UnknownBeans;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {


    //Custom Model
    @GET("api/unknown")
    Call<UnknownBeans> getUnknownApigeneral();

    //Response Body Method
    @GET("api/unknown")
    Call<ResponseBody> getUnknownApi();

    //Post Api
    @FormUrlEncoded
    @POST("api/unknown")
    Call<ResponseBody> postRetrofitApi(@Field("name") String name,@Field("job") String job);



}
