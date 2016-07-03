package com.smyhvae.radiogrouptabdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Administrator on 2016/6/30.
 */
public class WeatherActivity extends Activity {
    TextView txt_city;
    TextView txt_week;
    TextView txt_date;
    TextView txt_weather;
    TextView txt_temperature;
    TextView txt_ganmao;
    TextView txt_fengxiang;
    TextView txt_ziwaixian;
    TextView txt_date2;
    TextView txt_week2;
    TextView txt_tempature2;
    TextView txt_weather2;
    TextView txt_fengxiang2;
    TextView txt_date3;
    TextView txt_week3;
    TextView txt_tempature3;
    TextView txt_weather3;
    TextView txt_fengxiang3;
    TextView txt_date4;
    TextView txt_week4;
    TextView txt_tempature4;
    TextView txt_weather4;
    TextView txt_fengxiang4;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tianqi_layout);
        txt_city = (TextView) findViewById(R.id.txt_city);
        txt_week = (TextView) findViewById(R.id.txt_week);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_weather = (TextView) findViewById(R.id.txt_weather);
        txt_temperature= (TextView) findViewById(R.id.txt_temperature);
        txt_ganmao= (TextView) findViewById(R.id.txt_ganmao);
        txt_fengxiang= (TextView) findViewById(R.id.txt_fengxiang);
        txt_ziwaixian= (TextView) findViewById(R.id.txt_ziwaixian);
/*        txt_date2= (TextView) findViewById(R.id.txt_date2);
        txt_week2= (TextView) findViewById(R.id.txt_week2);
        txt_tempature2= (TextView) findViewById(R.id.txt_tempature2);
        txt_weather2= (TextView) findViewById(R.id.txt_weather2);
        txt_fengxiang2= (TextView) findViewById(R.id.txt_fengxiang2);

        txt_date3= (TextView) findViewById(R.id.txt_date3);
        txt_week3= (TextView) findViewById(R.id.txt_week3);
        txt_tempature3= (TextView) findViewById(R.id.txt_tempature3);
        txt_weather3= (TextView) findViewById(R.id.txt_weather3);
        txt_fengxiang3= (TextView) findViewById(R.id.txt_fengxiang3);

        txt_date4= (TextView) findViewById(R.id.txt_date4);
        txt_week4= (TextView) findViewById(R.id.txt_week4);
        txt_tempature4= (TextView) findViewById(R.id.txt_tempature4);
        txt_weather4= (TextView) findViewById(R.id.txt_weather4);
        txt_fengxiang4= (TextView) findViewById(R.id.txt_fengxiang4);*/

        openGPSSettings();
    }

    private void openGPSSettings() {
        LocationManager alm = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT)
                    .show();
            doWork();
            getweek();
            return;
        }
        Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivityForResult(intent, 0); //此为设置完成后返回到获取界面
    }
    private void doWork() {
        String msg = "";
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        // 获得最好的定位效果
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        // 使用省电模式
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        // 获得当前的位置提供者
        String provider = locationManager.getBestProvider(criteria, true);
        // 获得当前的位置

        location = locationManager.getLastKnownLocation(provider);
        Geocoder gc = new Geocoder(this);
        List<Address> addresses = null;
        try {
            addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0) {
            msg = addresses.get(0).getLocality() + "\n";
        }
        txt_city.setText(msg);

        msg = msg.substring(0, msg.length() - 2);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInteface baidu = retrofit.create(WeatherInteface.class);
        Call<Weather> call = baidu.getPhoneInfo("8e12d0585679836ab5330c18c5ff7209", msg);

        call.enqueue(new Callback<Weather>() {

            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather t = response.body();
                if ("success".equals(t.getErrMsg())) {
                    txt_weather.setText(t.getRetData().getToday().getType());
                    txt_temperature.setText(t.getRetData().getToday().getLowtemp()+"~"+t.getRetData().getToday().getHightemp());
                    txt_ganmao.setText(t.getRetData().getToday().getIndex().get(0).getDetails());
                    txt_fengxiang.setText(t.getRetData().getToday().getFengxiang()+" "+t.getRetData().getToday().getFengli());
                    txt_ziwaixian.setText(t.getRetData().getToday().getIndex().get(1).getDetails());

         /*           txt_date2.setText(t.getRetData().getForecast().get(0).getDate());
                    txt_week2.setText(t.getRetData().getForecast().get(0).getWeek());
                    txt_tempature2.setText(t.getRetData().getForecast().get(0).getLowtemp()+"~"+t.getRetData().getForecast().get(0).getHightemp());
                    txt_weather2.setText(t.getRetData().getForecast().get(0).getType());
                    txt_fengxiang2.setText(t.getRetData().getForecast().get(0).getFengxiang()+" "+t.getRetData().getForecast().get(0).getFengli());

                    txt_date3.setText(t.getRetData().getForecast().get(1).getDate());
                    txt_week3.setText(t.getRetData().getForecast().get(1).getWeek());
                    txt_tempature3.setText(t.getRetData().getForecast().get(1).getLowtemp()+"~"+t.getRetData().getForecast().get(1).getHightemp());
                    txt_weather3.setText(t.getRetData().getForecast().get(1).getType());
                    txt_fengxiang3.setText(t.getRetData().getForecast().get(1).getFengxiang()+" "+t.getRetData().getForecast().get(1).getFengli());

                    txt_date4.setText(t.getRetData().getForecast().get(2).getDate());
                    txt_week4.setText(t.getRetData().getForecast().get(2).getWeek());
                    txt_tempature4.setText(t.getRetData().getForecast().get(2).getLowtemp()+"~"+t.getRetData().getForecast().get(2).getHightemp());
                    txt_weather4.setText(t.getRetData().getForecast().get(2).getType());
                    txt_fengxiang4.setText(t.getRetData().getForecast().get(2).getFengxiang()+" "+t.getRetData().getForecast().get(2).getFengli());
*/
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

    }

    private void getweek() {
        String week = null;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String str = sdf.format(d);
        Calendar c = Calendar.getInstance();

        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                week = "周日";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
                break;
            default:
                break;
        }
        txt_week.setText(week);
        txt_date.setText(str);
    }
}
