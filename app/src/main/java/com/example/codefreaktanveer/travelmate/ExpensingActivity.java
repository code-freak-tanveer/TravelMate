package com.example.codefreaktanveer.travelmate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import model.DataMiddleware;
import model.Event;

public class ExpensingActivity extends AppCompatActivity {

    Context context;
    DataMiddleware databaseOperations;
    EditText expenseScopeEditText;
    EditText expenseAmountEditText;
    int event_id;
    Event event;
    Event event1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expensing);
        setTitle("Insert Expenses");
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.4));
        //dim view

        WindowManager.LayoutParams wlp = getWindow().getAttributes();
        wlp.gravity = Gravity.CENTER_VERTICAL;
        wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        wlp.dimAmount = (float) 0.7;
        getWindow().setAttributes(wlp);


        event_id = getIntent().getIntExtra("event_id", 0);

        expenseScopeEditText = (EditText) findViewById(R.id.expenseScope);
        expenseAmountEditText = (EditText) findViewById(R.id.expenseAmount);
    }

    public void SaveExpenseData(View view) {
        String expense_scope = expenseScopeEditText.getText().toString();
        String expense_amount = expenseAmountEditText.getText().toString();

        if(expense_scope.isEmpty()){
            expenseScopeEditText.setError("Expense Scope is required !");
        }
        if(expense_amount.isEmpty()){
            expenseAmountEditText.setError("Expense Amount is required !");
        }

        if ((expense_scope.isEmpty() == false) && (expense_amount.isEmpty() == false))
        {
            event = new Event(event_id, expense_scope, expense_amount);

            event1 = new Event(expense_amount);

            databaseOperations=new DataMiddleware(this);

                boolean inserted = databaseOperations.addEventExpense(event);
                boolean updated = databaseOperations.updateEventExpensing(event_id, event1);
                if(inserted){
                    Toast.makeText(getApplicationContext(),"Expense Added", Toast.LENGTH_SHORT).show();
                }


                Intent intentEventDetail = new Intent(ExpensingActivity.this, EventDetailActivity.class);
                intentEventDetail.putExtra("event_id", event_id);
               // startActivity(intentEventDetail);
        }
    }
}
