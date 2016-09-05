package com.example.codefreaktanveer.travelmate;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

import model.DataMiddleware;
import model.Event;

public class Insert_Event extends AppCompatActivity {

    Context context;
    EditText eventNameEditText;
    EditText eventLocationEditText;
    TextView eventStartDateTextView;
    TextView eventEndDateTextView;
    EditText eventBudgetEditText;
    Event event;
    DataMiddleware databaseOperations;
    int event_id;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__event);
        setTitle("Create Event");

        final Calendar c = Calendar.getInstance();

        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        eventNameEditText = (EditText) findViewById(R.id.eventName);
        eventLocationEditText = (EditText) findViewById(R.id.eventLocation);
        eventStartDateTextView = (TextView) findViewById(R.id.eventStartDate);
        eventEndDateTextView = (TextView) findViewById(R.id.eventEndDate);
        eventBudgetEditText = (EditText) findViewById(R.id.eventBudget);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        eventStartDateTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Insert_Event.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear + 1;
                        if(month < 10 && dayOfMonth >= 10){
                            eventStartDateTextView.setText(year + "-0" + month + "-" + dayOfMonth);
                        }
                        if(dayOfMonth < 10 && month >= 10) {
                            eventStartDateTextView.setText(year + "-" + month + "-0" + dayOfMonth);
                        }
                        if(dayOfMonth < 10 && month < 10) {
                            eventStartDateTextView.setText(year + "-0" + month + "-0" + dayOfMonth);
                        }
                        if(month >= 10 && dayOfMonth >= 10){
                            eventStartDateTextView.setText(year + "-" + month + "-" + dayOfMonth);
                        }
                    }
                }, year, month, day);
                datePickerDialog.setTitle("Select Start Date");
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

        eventEndDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Insert_Event.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear + 1;
                        if(month < 10 && dayOfMonth >= 10){
                            eventEndDateTextView.setText(year + "-0" + month + "-" + dayOfMonth);
                        }
                        if(dayOfMonth < 10 && month >= 10) {
                            eventEndDateTextView.setText(year + "-" + month + "-0" + dayOfMonth);
                        }
                        if(dayOfMonth < 10 && month < 10) {
                            eventEndDateTextView.setText(year + "-0" + month + "-0" + dayOfMonth);
                        }
                        if(month >= 10 && dayOfMonth >= 10){
                            eventEndDateTextView.setText(year + "-" + month + "-" + dayOfMonth);
                        }
                    }
                },year,month,day);
                datePickerDialog.setTitle("Select End Date");
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });


        event_id = getIntent().getIntExtra("event_id", 0);
        DataMiddleware databaseOperations = new DataMiddleware(this);

        if(event_id > 0){
            Event event = databaseOperations.getEvent(event_id);

            eventNameEditText.setText(event.getEvent_name());
            eventLocationEditText.setText(event.getEvent_location());
            eventStartDateTextView.setText(event.getEvent_start_date());
            eventEndDateTextView.setText(event.getEvent_end_date());
            eventBudgetEditText.setText(event.getEvent_budget());
            saveBtn.setText("Update Event");
            setTitle("Update Event");
        }
    }

    @Override
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
        builder.setMessage("If you go back, inputed data will be lost!")
                .setPositiveButton("Sure", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void SaveEventData(View view) {
        String eventNm = eventNameEditText.getText().toString();
        String eventLoc = eventLocationEditText.getText().toString();
        String eventStartDt = eventStartDateTextView.getText().toString();
        String eventEndDt = eventEndDateTextView.getText().toString();
        String eventBdgt = eventBudgetEditText.getText().toString();

        if(eventNm.isEmpty()){
            eventNameEditText.setError("Event Name is required !");
        }
        if(eventLoc.isEmpty()){
            eventLocationEditText.setError("Event Name is required !");
        }
        if(eventStartDt.isEmpty()){
            eventStartDateTextView.setError("Start Date is required !");
        }
        if(eventEndDt.isEmpty()){
            eventEndDateTextView.setError("End Date is required !");
        }
        if(eventBdgt.isEmpty()){
            eventBudgetEditText.setError("Budget is required !");
        }

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date myDate = dateFormat.parse(eventStartDt);
//
//            Toast.makeText(Insert_Event.this, myDate+"", Toast.LENGTH_SHORT).show();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        if ((eventNm.isEmpty() == false) && (eventLoc.isEmpty() == false) && (eventStartDt.isEmpty() == false) && (eventEndDt.isEmpty() == false) && (eventBdgt.isEmpty() == false))
        {
            event = new Event(eventNm, eventLoc, eventStartDt, eventEndDt, eventBdgt);

            databaseOperations=new DataMiddleware(this);

            if(event_id>0){
                boolean updated = databaseOperations.updateEvent(event_id,event);
                Toast.makeText(getApplicationContext(), "Event Updated.", Toast.LENGTH_SHORT).show();
            }else{
                boolean inserted = databaseOperations.addEvent(event);
                Toast.makeText(getApplicationContext(), "Event Inserted", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), EventActivity.class);
            startActivity(intent);
        }
    }



}
