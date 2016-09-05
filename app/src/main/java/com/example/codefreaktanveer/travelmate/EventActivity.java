package com.example.codefreaktanveer.travelmate;


import android.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import model.DataMiddleware;
import model.Event;
import model.User;

public class EventActivity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    private Toolbar toolbar;
    View nav_header;
  private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView noDataFoundtextView;
    private DataMiddleware databaseOperations;
    private ArrayList<Event>  events;
    private ListView eventListView;
    private EventAdapter eventAdapter;
    private CircleImageView imageView;
    TextView txtName;
    TextView txtEmail;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private static double lat;
    private static double longitude;



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.event_option_menu,menu);
        if (v.getId() == R.id.eventList) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        }

            super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Event eventDetail = (Event) eventListView.getAdapter().getItem(info.position);
        int eid = eventDetail.getId();
        int itemId=item.getItemId();
        String startDate=eventDetail.getEvent_start_date();


        switch (itemId){
            case R.id.optionEdit:
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String todayDate =  dateFormatter.format(date);



                try{
                    Date date1 = dateFormatter.parse(todayDate);
                    Date date2 = dateFormatter.parse(startDate);


                    if(date1.after(date2)){
                        Toast.makeText(this, "Date1 is after Date2", Toast.LENGTH_SHORT).show();
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int choice) {
                                switch (choice) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        break;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setMessage("Sorry, You are unable to Edit.")
                                .setPositiveButton("Ok", dialogClickListener).show();
                        Toast.makeText(getApplicationContext(), "Event is GOING ON/PASSED-AWAY.", Toast.LENGTH_SHORT).show();
                    }

                    if(date1.before(date2)){
//                        Toast.makeText(context, "Date1 is before Date2", Toast.LENGTH_SHORT).show();
                        Intent updateIntent = new Intent(this, Insert_Event.class);
                        updateIntent.putExtra("event_id", eid);
                        startActivity(updateIntent);

                    }

                    if(date1.equals(date2)){
//                        Toast.makeText(context, "Date1 is equal Date2", Toast.LENGTH_SHORT).show();
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int choice) {
                                switch (choice) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        break;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Sorry, You are unable to Edit.")
                                .setPositiveButton("Ok", dialogClickListener).show();
                        Toast.makeText(this, "Today is Event Starting Day", Toast.LENGTH_SHORT).show();
                    }

                }catch(ParseException ex){
                    Log.e("ERRORS",ex.getMessage());
                }


                break;
            case R.id.optionViewExpense:
                Intent expenseDetailIntent=new Intent(this,ExpensingActivity.class);
                expenseDetailIntent.putExtra("event_id",eid);
                startActivity(expenseDetailIntent);
                break;
            case R.id.optionAddExpense:
                Intent expenseIntent=new Intent(this,ExpenseDetailActivity.class);
                expenseIntent.putExtra("event_id",eid);
                startActivity(expenseIntent);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);
        toolbar= (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();


        // This code is imported from Nipun
        setTitle("Events");

        noDataFoundtextView = (TextView) findViewById(R.id.noDataFound);


        databaseOperations = new DataMiddleware(this);

        events = databaseOperations.getAllEvents();
        if (events.isEmpty() == true){
            noDataFoundtextView.setText("No Event Set Yet");
            noDataFoundtextView.setVisibility(View.VISIBLE);
        }

        if(events.isEmpty() == false){
            eventListView = (ListView) findViewById(R.id.eventList);
            eventAdapter = new EventAdapter(this, events);
            eventListView.setAdapter(eventAdapter);
            registerForContextMenu(eventListView);
            noDataFoundtextView.setVisibility(View.INVISIBLE);
        }
// End of code
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawerLayout= (DrawerLayout) findViewById(R.id.drawer);

        navigationView= (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        nav_header = navigationView.getHeaderView(0);
        imageView=(CircleImageView)nav_header.findViewById(R.id.profile_image);
        txtName= (TextView) nav_header.findViewById(R.id.username);
        txtEmail = (TextView) nav_header.findViewById(R.id.email);
        SharedPreferences pref=getSharedPreferences("tourMate",MODE_PRIVATE);
        String userName=pref.getString("userName",null);
        User user=databaseOperations.getUserData(userName);
        txtEmail.setText(user.getEmail());
        txtName.setText(user.getFullName());
        Toast.makeText(EventActivity.this, user.getImage(), Toast.LENGTH_SHORT).show();
        try {
            imageView.setImageBitmap(ImageLoader.init().from(user.getImage()).requestSize(70,70).getBitmap());

        } catch (FileNotFoundException e) {
           // Log.e("LoadError",e.getMessage());
        }


        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,
                R.string.closeDrawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }



        };
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id=item.getItemId();

                if(id==R.id.weatherView){
                    Intent intent=new Intent(EventActivity.this,Weather.class);
                    intent.putExtra("latitude",lat);
                    intent.putExtra("longitude",longitude);
                    startActivity(intent);
                }else if(id==R.id.nearby){
                    Intent locationIntent=new Intent(EventActivity.this,NearbyView.class);
                    startActivity(locationIntent);
                }
                else if(id==R.id.signOut){
                    SharedPreferences pref=getSharedPreferences("tourMate",MODE_PRIVATE);
                    pref.edit().remove("userName").commit();
                    Intent locationIntent=new Intent(EventActivity.this,LoginActivity.class);
                    startActivity(locationIntent);
                }
                return false;
            }
        });




    }

    public void addEvent(View view) {
        Intent intent = new Intent(getApplicationContext(), Insert_Event.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intenteded=new Intent(EventActivity.this,SignupActivity.class);
        startActivity(intenteded);
        Toast.makeText(EventActivity.this, "Home Page", Toast.LENGTH_SHORT).show();
    }

    public void UpdateEvent(View view) {
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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



}
