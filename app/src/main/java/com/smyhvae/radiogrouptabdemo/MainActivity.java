package com.smyhvae.radiogrouptabdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FatherBarActivity {
    ToggleButton tb;
    RelativeLayout re;
    EditText username;
    EditText pwd;
    Intent i;
    @Override
    public void child(Bundle savedInstanceState) {
        setContentView(R.layout.layout_login);
        re= (RelativeLayout) findViewById(R.id.back);
        re.setBackgroundResource(R.drawable.login7);
        tb= (ToggleButton) findViewById(R.id.tgbtn_show_psw);
        pwd= (EditText) findViewById(R.id.tv2);
        pwd.setInputType(0x81);
        username= (EditText) findViewById(R.id.tv1);

        Toolbar t = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(t);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    pwd.setInputType(0x90);
                }else {
                    pwd.setInputType(0x81);
                }
            }
        });
    }
    public void login(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.90:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final String LoginName = username.getText().toString();
        String Loginpwd = pwd.getText().toString();
        Userinfo user=retrofit.create(Userinfo.class);
        Call<User> call = user.finduserinfo(LoginName,Loginpwd);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                   if(response.body().equals(LoginName)){
                       Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                        i=new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(i);
                    }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

        /*       Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                i=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(i);*/
            }
        });
    }

}
