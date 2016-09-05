package com.example.codefreaktanveer.travelmate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import model.CoordinateApi;
import model.DataMiddleware;
import model.Event;
import model.LocationData;
import model.MainLocationData;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventDetailActivity extends AppCompatActivity {

    int event_id;
    private Retrofit retrofit;
    DataMiddleware databaseOperations;
    Event event;
    TextView eventTitleTextView;
    TextView eventLocationTextView;
    TextView startDateTextView;
    TextView endDateTextView;
    TextView eventBudgetTextView;
    TextView eventMoneyUsedTextView;
    ImageButton expensesImageBtn;
    ImageButton expenseDetailImageBtn;
    ListView momentListView;
    ArrayList<ImageMoment> imageMoments;
    ImageAdapter imageAdapter;
    TextView noDataFoundTextView;

    NavigationView navigationView;
    private CoordinateApi coordinateApi;
    private static double longitude;
    private static double lat;
    private View nav_header;
    private CircleImageView imageView;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        setTitle("Event Detail");

        eventTitleTextView= (TextView) findViewById(R.id.eventTitle);
        eventLocationTextView= (TextView) findViewById(R.id.eventLocation);
        startDateTextView= (TextView) findViewById(R.id.startDate);
        endDateTextView= (TextView) findViewById(R.id.endDate);
        eventBudgetTextView= (TextView) findViewById(R.id.eventBudget);
        eventMoneyUsedTextView= (TextView) findViewById(R.id.eventMoneyUsed);
        expensesImageBtn = (ImageButton) findViewById(R.id.expensesBtn);
        expenseDetailImageBtn = (ImageButton) findViewById(R.id.expenseDetail);
        noDataFoundTextView = (TextView) findViewById(R.id.noDataFound);
        momentListView = (ListView) findViewById(R.id.momentList);
        navigationView= (NavigationView) findViewById(R.id.navigation_view);
        navigationView= (NavigationView) findViewById(R.id.navigation_view);
        databaseOperations = new DataMiddleware(this);
        nav_header = navigationView.getHeaderView(0);
        imageView=(CircleImageView)nav_header.findViewById(R.id.profile_image);
        txtName= (TextView) nav_header.findViewById(R.id.username);
        SharedPreferences pref=getSharedPreferences("tourMate",MODE_PRIVATE);
        String userName=pref.getString("userName",null);
        User user=databaseOperations.getUserData(userName);
        txtName.setText(user.getFullName());

        try {
            imageView.setImageBitmap(ImageLoader.init().from(user.getImage()).requestSize(70,70).getBitmap());

        } catch (FileNotFoundException e) {

        }
        event_id = getIntent().getIntExtra("event_id", 0);




        imageMoments = databaseOperations.getAllMomentsByEvent(event_id);

        if(imageMoments.isEmpty() == true){
            noDataFoundTextView.setVisibility(View.VISIBLE);
            noDataFoundTextView.setText("No Moments Found");
        }

        if(imageMoments.isEmpty() == false){
            imageAdapter = new ImageAdapter(this, imageMoments);
            momentListView.setAdapter(imageAdapter);
            noDataFoundTextView.setVisibility(View.GONE);
        }

        event = databaseOperations.getEvent(event_id);

        String event_name = event.getEvent_name();
        String event_loc = event.getEvent_location();
        String event_start_date = event.getEvent_start_date();
        String event_end_date = event.getEvent_end_date();
        String event_budget = event.getEvent_budget();
        String event_money_used = event.getEvent_expenses();

        eventTitleTextView.setText(event_name);
        eventLocationTextView.setText(event_loc);
        startDateTextView.setText(event_start_date);
        endDateTextView.setText(event_end_date);
        eventBudgetTextView.setText("Budget:"+event_budget+"Tk");
        eventMoneyUsedTextView.setText("Used:"+event_money_used+"Tk");
        // Place data
        getData(event_loc);
        expensesImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExpense = new Intent(EventDetailActivity.this, ExpensingActivity.class);
                intentExpense.putExtra("event_id", event_id);
                startActivity(intentExpense);
            }
        });

        expenseDetailImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExpense = new Intent(EventDetailActivity.this, ExpenseDetailActivity.class);
                intentExpense.putExtra("event_id", event_id);
                startActivity(intentExpense);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id=item.getItemId();

                if(id==R.id.weatherView){

                    Intent intent=new Intent(EventDetailActivity.this,Weather.class);
                    intent.putExtra("latitude",lat);
                    intent.putExtra("longitude",longitude);
                    startActivity(intent);
                }
                return false;
            }
        });
//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new ImageAdapter(this));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(getApplicationContext(), "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int choice) {
                switch (choice) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                        startActivity(intent);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you Want to go Home Page?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void addMoment(View view) {
        Intent intent = new Intent(EventDetailActivity.this, MomentActivity.class);
        intent.putExtra("event_id", event_id);
        startActivity(intent);
    }
    private  void getData(String place){
         MainLocationData.Result.Geometry.Location location;
                retrofit=new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/geocode/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        coordinateApi= retrofit.create(CoordinateApi.class);
//
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("address",place);
        map.put("key","AIzaSyCOeqdFa4S6MMPXs20mb8l2FasHFrR3OuE");
        Call<MainLocationData> locationBody=coordinateApi.getlocation(map);
        Log.e("jasjdhgajgsdjahgsdkja",locationBody.request().url().toString());
        locationBody.enqueue(new Callback<MainLocationData>() {
            @Override
            public void onResponse(Call<MainLocationData> call, Response<MainLocationData> response) {
                MainLocationData data=response.body();
                List<MainLocationData.Result> resultList=data.getResults();
                MainLocationData.Result.Geometry geo=resultList.get(0).getGeometry();
                MainLocationData.Result.Geometry.Location loc =geo.getLocation();
                lat=loc.getLat();
                longitude=loc.getLng();



            }
            @Override
            public void onFailure(Call<MainLocationData> call, Throwable t) {

            }
        });

    }
}