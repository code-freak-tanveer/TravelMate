package com.example.codefreaktanveer.travelmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Event;

/**
 * Created by Nipun on 22-Aug-16.
 */
public class ExpenseAdapter extends ArrayAdapter<Event> {
    private Context context;
    private ArrayList<Event> events;

    public ExpenseAdapter(Context context, ArrayList<Event> events) {
        super(context, R.layout.expense_list_view, events);
        this.context = context;
        this.events=events;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.expense_list_view,null,true);
        TextView expenseScopeTextView = (TextView) convertView.findViewById(R.id.expenseScopeTxt);
        TextView expenseAmountTextView = (TextView) convertView.findViewById(R.id.expenseAmountTxt);


        expenseScopeTextView.setText(events.get(position).getExpense_scope());
        expenseAmountTextView.setText(events.get(position).getExpense_amount() + "TK");

        return convertView;
    }
}
