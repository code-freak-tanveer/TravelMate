package com.example.codefreaktanveer.travelmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import model.DataMiddleware;
import model.Event;

public class ExpenseActivity extends AppCompatActivity {

    ListView expenseListView;
    ExpenseAdapter expenseAdapter;
    DataMiddleware databaseOperations;
    ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        int event_id = getIntent().getIntExtra("event_id", 0);

        Toast.makeText(ExpenseActivity.this, event_id+"", Toast.LENGTH_SHORT).show();

        databaseOperations = new DataMiddleware(this);

        events = databaseOperations.getExpensesByEvent(event_id);



        expenseListView = (ListView) findViewById(R.id.expenseList);
        expenseAdapter = new ExpenseAdapter(this, events);
        expenseListView.setAdapter(expenseAdapter);
    }
}
