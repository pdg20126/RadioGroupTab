package com.smyhvae.radiogrouptabdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/7/4.
 */
public interface Userinfo {

    @GET("/pdgweb/login")
    Call<User> finduserinfo(@Query("LoginName") String loginuser, @Query("Loginpwd") String loginpwd);
}
