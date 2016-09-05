package com.example.codefreaktanveer.travelmate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import model.WeatherApi;
import retrofit2.Callback;

/**
 * Created by Code Freak Tanveer on 01/09/2016.
 */
public class ForcastFragment extends Fragment {
    private  ArrayList<WeatherApi.Daily.Datum_> datum_List;
    private Context context;

    public ForcastFragment(Context context, ArrayList<WeatherApi.Daily.Datum_> datum_List) {
        this.context=context;
        this.datum_List=datum_List;
    }

    private ListView lstWeather;
    WeatherListAdapdet adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.layout_forcast, container, false);
        lstWeather= (ListView) v.findViewById(R.id.lstweather);
            adapter=new WeatherListAdapdet(context,datum_List);
            lstWeather.setAdapter(adapter);
        return v;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
    }

}
