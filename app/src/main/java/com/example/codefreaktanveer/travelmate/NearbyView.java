package com.example.codefreaktanveer.travelmate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.LocationData;
import model.LocationParser;
import model.Parser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearbyView extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    private Retrofit retrofit;
    private LocationParser locationParser;
    private ListView lstLocations;
    private NeartbyListAdapter adapter;
    private static double lat;
    private static double longitude;
    private static String locationtype="restaurant";

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Spinner s;
    private final String placeTypeList[]={"restaurant","hospital","bus_station","atm","liquor_store","bank" +
            "","bar","mosque","car_rental","car_repair","gas_station","laundry"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_view);
        s = (Spinner) findViewById(R.id.typeSpinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationtype=placeTypeList[position];
                getData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lstLocations= (ListView) findViewById(R.id.lstNearbyLocations);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        initNetwork();
        googleApiClient.connect();
        getData();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        googleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        googleApiClient.disconnect();
    }

    @Override

    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_COARSE_LOCATION"},1);
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
       lat=location.getLatitude();
        longitude=location.getLongitude();



    }




    private void getData() {

        HashMap<String,String> paramiter=new HashMap<>();
        paramiter.put("location",""+lat+","+longitude+"");
        Log.e("Current loc",""+lat+","+longitude+"");
        paramiter.put("radius","500");
        paramiter.put("type",locationtype);

        paramiter.put("key","AIzaSyCOeqdFa4S6MMPXs20mb8l2FasHFrR3OuE");
         final Call<LocationData> locationData=locationParser.getLocations(paramiter);
        locationData.enqueue(new Callback<LocationData>() {
            @Override
            public void onResponse(Call<LocationData> call, Response<LocationData> response) {
            LocationData locationdata=response.body();
                final ArrayList<LocationData.Result> resultList= (ArrayList<LocationData.Result>) locationdata.getResults();
                adapter=new NeartbyListAdapter(getApplicationContext(),resultList);
                lstLocations.setAdapter(adapter);
                lstLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(NearbyView.this, position+"", Toast.LENGTH_SHORT).show();
                        LocationData.Result result= resultList.get(position);
                        LocationData.Result.Geometry  geo=result.getGeometry();
                        LocationData.Result.Geometry.Location location=geo.getLocation();

                        Intent intent=new Intent(NearbyView.this,MapsActivity.class);
                        intent.putExtra("destLat",location.getLat());
                        intent.putExtra("destLong",location.getLng());
                        intent.putExtra("currentLat",lat);
                        intent.putExtra("currentLong",longitude);
                        startActivity(intent);
                    }
                });
               // Log.e("url", "getData: "+locationData.request().url());
            }

            @Override
            public void onFailure(Call<LocationData> call, Throwable t) {
                Toast.makeText(NearbyView.this, "Faild to grab data", Toast.LENGTH_SHORT).show();
                Log.e("CONNECTION ERROR:......",t.getMessage());
            }

        });
    }

    public void initNetwork(){
        int permission= ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if(permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{ Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE},
                    1

            );
        }

        retrofit=new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        locationParser= retrofit.create(LocationParser.class);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
