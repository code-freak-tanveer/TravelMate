package com.example.codefreaktanveer.travelmate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.Event;

/**
 * Created by Nipun on 19-Aug-16.
 */
public class EventAdapter extends ArrayAdapter<Event>{
    private Context context;
    private ArrayList<Event>events;


    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, R.layout.event_list_view, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        LinearLayout convertView1 = (LinearLayout) convertView;

        convertView = inflater.inflate(R.layout.event_list_view,null,true);

//        convertView.setBackgroundColor(Color.GREEN);

        TextView eventNameTextView = (TextView) convertView.findViewById(R.id.eventNameTitle);
        TextView eventStartDateTextView = (TextView) convertView.findViewById(R.id.eventStartDate);
        TextView eventBudgetTextView = (TextView) convertView.findViewById(R.id.eventBudget);
        TextView eventUsedMoneyTextView = (TextView) convertView.findViewById(R.id.eventExpenses);


        eventNameTextView.setText(events.get(position).getEvent_name());
        eventStartDateTextView.setText(events.get(position).getEvent_start_date());
        eventBudgetTextView.setText(events.get(position).getEvent_budget() + "Tk");
        eventUsedMoneyTextView.setText(events.get(position).getEvent_expenses() + "Tk");

        Double budget_dbl = Double.parseDouble(events.get(position).getEvent_budget());
        Double expense_dbl = Double.parseDouble(events.get(position).getEvent_expenses());

        if(budget_dbl / 2 == expense_dbl){
            //((TextView) convertView.findViewById(R.id.eventBudget)).setTextColor(Color.BLACK);
            ((TextView) convertView.findViewById(R.id.eventBudget)).setTextColor(Color.YELLOW);
        }
        if(budget_dbl / 2 < expense_dbl){
            ((TextView) convertView.findViewById(R.id.eventBudget)).setTextColor(Color.RED);
        }
        if(budget_dbl / 2 > expense_dbl){
            ((TextView) convertView.findViewById(R.id.eventBudget)).setTextColor(Color.rgb(24,166,17));
           // convertView.findViewById(R.id.eventBudget).setBackgroundColor(Color.GREEN);
        }
        eventNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int event_id = events.get(position).getId();


                Intent detailEventIntent = new Intent(context, EventDetailActivity.class);
                detailEventIntent.putExtra("event_id", event_id);
                context.startActivity(detailEventIntent);
            }
        });




//        editImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int choice) {
//                        switch (choice) {
//                            case DialogInterface.BUTTON_POSITIVE:
//                                int event_id = events.get(position).getId();
//                                Intent updateIntent = new Intent(context, Insert_Event.class);
//                                updateIntent.putExtra("event_id", event_id);
//                                context.startActivity(updateIntent);
//                                break;
//                            case DialogInterface.BUTTON_NEGATIVE:
//                                break;
//                        }
//                    }
//                };
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Do you Want to Edit This Event?")
//                        .setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
//            }
//        });

        return convertView;
    }
}
