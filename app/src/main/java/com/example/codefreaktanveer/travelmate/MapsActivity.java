package com.example.codefreaktanveer.travelmate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.RequestResult;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.GeocodedWaypoint;
import com.akexorcist.googledirection.model.Info;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.activity_fragment_mapview);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        double destLat=getIntent().getDoubleExtra("destLat",80);
        double destLong=getIntent().getDoubleExtra("destLong",80);
        double currentLat=getIntent().getDoubleExtra("currentLat",80);
        double currentLong=getIntent().getDoubleExtra("currentLong",80);


        LatLng dest = new LatLng(destLat, destLong);
        LatLng current = new LatLng(currentLat, currentLong);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 1);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);


        map.addMarker(new MarkerOptions()
                .title("Desination")
                .snippet("The most populous city")
                .position(dest));
        map.addMarker(new MarkerOptions()
                .title("Current")
                .snippet("The most populous city in")
                .position(current));

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(current, 13));
// Old style code
//        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
//
//            options.add(current);
//            options.add(dest);
//
//        map.addPolyline(options);
// code end
        GoogleDirection.withServerKey("AIzaSyCjKI9ggRzvRlYWUYolHnb1v7G1OEsdmCE")
                .from(current)
                .to(dest)

                .execute(new DirectionCallback() {

                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        String status = direction.getStatus();
                        if(status.equals(RequestResult.OK)) {
                            Route route = direction.getRouteList().get(0);
                            Leg leg = route.getLegList().get(0);

                            ArrayList<LatLng> sectionList = leg.getSectionPoint();
                            PolylineOptions polylineOptions = DirectionConverter.createPolyline(getApplicationContext(), sectionList, 5, Color.RED);
                            map.addPolyline(polylineOptions);
                            String distanceInfo = leg.getDistance().getText();
                            String durationInfo = leg.getDuration().getText();
                            final Snackbar snackbar= Snackbar.make(findViewById(R.id.mapParent),"Distance: "+distanceInfo+"\nApporimately "+durationInfo+" needed",
                                    Snackbar.LENGTH_INDEFINITE );
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    snackbar.dismiss();
                                }
                            });
                                   snackbar .show();

//                            List<Step> steplist = leg.getStepList();
//                            for(Step step:steplist){
//                                LatLng start = step.getStartLocation().getCoordination();
//                                LatLng end = step.getEndLocation().getCoordination();
//                            }

                        } else {

                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                    }
                });
    }
}

