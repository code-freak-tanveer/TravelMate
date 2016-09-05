package com.example.codefreaktanveer.travelmate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.WeatherApi;

/**
 * Created by Code Freak Tanveer on 01/09/2016.
 */
public class CurrentWeatherFragment extends Fragment {
    private TextView txtTemp;
    private TextView txtApparentTemp;
    private TextView txtrain;
    private TextView txtcloudCover;
    private TextView txtDate;
    private WeatherApi.Currently currentData;
    private String sosplayTemp;
    private String rainnString;
    private String feelString;
    private  String cloudCover;

    public CurrentWeatherFragment(WeatherApi.Currently currently) {
        this.currentData=currently;
    }

    public CurrentWeatherFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            double temp=currentData.getTemperature();
            double cloud=currentData.getCloudCover();
            double feel=currentData.getApparentTemperature();
            double rain=currentData.getPrecipProbability();

            sosplayTemp=temp+"\u00b0F";
            feelString="Feel like :"+feel+"\u00b0F";
            NumberFormat nf=new DecimalFormat("#0.00");
            rainnString="percipitation :"+(nf.format(rain*100)+"%");
            cloudCover="Cloudy";
            int tempCloud=(int)(cloud*10);
            switch (tempCloud){
                case 1:cloudCover="Clear ";break;
                case 2:cloudCover="Clear ";break;
                case 3:cloudCover="Few clouds ";break;
                case 4:cloudCover="partly cloudy ";break;
                case 5:cloudCover="Hazzy ";break;
                case 6:cloudCover="mostly Cloudy ";break;
                case 7:cloudCover="cloudy ";break;
                case 8:cloudCover="Heavy cloud ";break;
                case 9:cloudCover="Heavy cloudy ";break;
            }

        }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v= inflater.inflate(R.layout.layout_current_weather_fragment, container, false);
        txtTemp= (TextView) v.findViewById(R.id.txtTemp);
        txtApparentTemp= (TextView) v.findViewById(R.id.txtApparentTemp);
        txtcloudCover= (TextView) v.findViewById(R.id.cloudCover);
        txtrain= (TextView) v.findViewById(R.id.precipitation);
        txtDate= (TextView) v.findViewById(R.id.txtDate);

//        txtTemp.setText("29\u00b0F");
//        txtApparentTemp.setText("33\u00b0F");
        txtTemp.setText(sosplayTemp);
        txtApparentTemp.setText(feelString);
        txtrain.setText(rainnString);
        txtcloudCover.setText(cloudCover);
        DateFormat dateFormat = new SimpleDateFormat("EE, yyyy/MM/dd");
        Date date = new Date();
        txtDate.setText(dateFormat.format(date));

        return v;
    }

}
