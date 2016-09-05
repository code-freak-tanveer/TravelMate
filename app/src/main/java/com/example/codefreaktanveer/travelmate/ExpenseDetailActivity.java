package com.example.codefreaktanveer.travelmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.DataMiddleware;
import model.Event;

public class ExpenseDetailActivity extends AppCompatActivity {

    ListView expenseListView;
    ExpenseAdapter expenseAdapter;
    DataMiddleware databaseOperations;
    ArrayList<Event> events;
    Event event;
    TextView noDataFoundTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        noDataFoundTextView = (TextView) findViewById(R.id.noDataFound);
        int event_id = getIntent().getIntExtra("event_id", 0);

        databaseOperations = new DataMiddleware(this);

        events = databaseOperations.getExpensesByEvent(event_id);
        event = databaseOperations.getEvent(event_id);

        if(events.isEmpty() == false){
            setTitle(event.getEvent_name());
            expenseListView = (ListView) findViewById(R.id.expenseList);
            expenseAdapter = new ExpenseAdapter(this, events);
            expenseListView.setAdapter(expenseAdapter);
            noDataFoundTextView.setVisibility(View.INVISIBLE);
        }
        if(events.isEmpty() == true){
            setTitle(event.getEvent_name());
            noDataFoundTextView.setVisibility(View.VISIBLE);
            noDataFoundTextView.setText("No Expenses Found");
        }

    }
}
