package com.smyhvae.radiogrouptabdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/6/30.
 */
public interface WeatherInteface {
    @GET("/apistore/weatherservice/recentweathers")
    Call<Weather> getPhoneInfo(@Header("apikey") String apikey, @Query("cityname") String cityname);
}
