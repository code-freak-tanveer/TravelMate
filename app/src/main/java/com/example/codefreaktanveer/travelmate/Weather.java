package com.example.codefreaktanveer.travelmate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Parser;
import model.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Weather extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE =1 ;
    private Retrofit retrofit;
    private Parser parser;
    private ListView lstWeather;
    private ViewPager viewPager;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);


        initNetwork();
        getData();
    }
    public void initNetwork(){
        int permission= ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if(permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{ Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE},
                    REQUEST_EXTERNAL_STORAGE

            );
        }

        retrofit=new Retrofit.Builder()
                .baseUrl("https://api.forecast.io/forecast/113520afbe88842421d0f6247f8ab091/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        parser= retrofit.create(Parser.class);


    }

    public void getData() {
        double lat=getIntent().getDoubleExtra("latitude",23);
        double longitude=getIntent().getDoubleExtra("longitude",23);


        Call<WeatherApi> weatherData=parser.getWeatherCondition(lat,longitude);
        weatherData.enqueue(new Callback<WeatherApi>() {
            @Override
            public void onResponse(Call<WeatherApi> call, Response<WeatherApi> response) {
            WeatherApi weatherData=response.body();
            WeatherApi.Daily dailyWeather=weatherData.getDaily();
                WeatherApi.Currently currentData=weatherData.getCurrently();
            ArrayList<WeatherApi.Daily.Datum_> datum_List= (ArrayList<WeatherApi.Daily.Datum_>) dailyWeather.getData();
                CurrentWeatherFragment currentWeather=new CurrentWeatherFragment(currentData);
                ForcastFragment forcast=new ForcastFragment(getApplicationContext(),datum_List);
                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                adapter.addFragment(currentWeather, "ONE");
                adapter.addFragment(forcast, "TWO");

                viewPager.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<WeatherApi> call, Throwable t) {


                Toast.makeText(Weather.this, "Faild to grab data", Toast.LENGTH_SHORT).show();
                Log.e("CONNECTION ERROR:......",t.getMessage());
            }

        });

    }
}
