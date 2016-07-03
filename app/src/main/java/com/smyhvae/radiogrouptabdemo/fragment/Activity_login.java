package com.smyhvae.radiogrouptabdemo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.smyhvae.radiogrouptabdemo.HomeActivity;
import com.smyhvae.radiogrouptabdemo.R;
import com.smyhvae.radiogrouptabdemo.Userinfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/6/24.
 */
public class Activity_login extends Activity{
    EditText username;
    EditText pwd;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        username= (EditText) findViewById(R.id.tv_uid);
        pwd= (EditText) findViewById(R.id.edit_uid);

    }
    public void login(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.191:8001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        String uname = username.getText().toString();
        final String userpwd = pwd.getText().toString();
        final Userinfo user=retrofit.create(Userinfo.class);
        Call<Userinfo> call = user.finduserinfo(uname,userpwd);
        call.enqueue(new Callback<Userinfo>() {
            @Override
            public void onResponse(Call<Userinfo> call, Response<Userinfo> response) {
                    if(response.body().equals("success")){
                        i=new Intent(Activity_login.this,HomeActivity.class);
                        startActivity(i);
                    }
            }
            @Override
            public void onFailure(Call<Userinfo> call, Throwable t) {
                Toast.makeText(Activity_login.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
