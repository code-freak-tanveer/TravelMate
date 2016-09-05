package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.codefreaktanveer.travelmate.ImageMoment;

import java.util.ArrayList;

/**
 * Created by Code Freak Tanveer on 13/08/2016.
 */
public class DataMiddleware {
    private SQLiteDatabase database;
    private DatabaseHelper helper;
    private Event event;
    private ImageMoment imageMoment;

    public DataMiddleware(Context context) {
        helper=new DatabaseHelper(context);
    }
    public void open(){
        database=helper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }
    public boolean registerUser(User user){
        String userName=user.getUserName();
        String password=user.getPassword();
        String fullName=user.getFullName();
        String email=user.getEmail();
        String image=user.getImage();


        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_USER_NAME,userName);
        contentValues.put(DatabaseHelper.COL_PASSWORD,password);
        contentValues.put(DatabaseHelper.COL_FULL_NAME,fullName);
        contentValues.put(DatabaseHelper.COL_EMAIL,email);
        contentValues.put(DatabaseHelper.COL_IMAGE,image);

        open();
        long inserted=database.insert(DatabaseHelper.TABLE_USER,null,contentValues);
        close();
        if(inserted>=0){
            return true;
        }
        return false;
    }
    public boolean checkLogin(String email, String password){
        open();
        Cursor cursor=
        database.query(DatabaseHelper.TABLE_USER,new String[]{DatabaseHelper.COL_EMAIL +
                ""},DatabaseHelper.COL_EMAIL+" = ? AND " +
                ""+DatabaseHelper.COL_PASSWORD+" =?",new String[]{email,password},null,null,null);

        cursor.moveToFirst();
        if(cursor.getCount()>0){
            close();
            return true;
        }
        close();
        return false;
    }
    public boolean addEvent(Event event){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_EVENT_NAME,event.getEvent_name());
        contentValues.put(DatabaseHelper.COL_EVENT_LOCATION,event.getEvent_location());
        contentValues.put(DatabaseHelper.COL_EVENT_START_DATE, event.getEvent_start_date());
        contentValues.put(DatabaseHelper.COL_EVENT_END_DATE, event.getEvent_end_date());
        contentValues.put(DatabaseHelper.COL_EVENT_BUDGET, event.getEvent_budget());
        contentValues.put(DatabaseHelper.COL_EVENT_EXPENSES, 0);

        long inserted = database.insert(DatabaseHelper.TABLE_EVENT,null,contentValues);

        this.close();
        if(inserted>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean addMoment(ImageMoment imageMoment){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_EVENT_ID_FOREIGN_2,imageMoment.getEvent_id());
        contentValues.put(DatabaseHelper.COL_MOMENT_NAME,imageMoment.getMoment_name());
        contentValues.put(DatabaseHelper.COL_MOMENT_DATE_TIME,imageMoment.getMoment_date_time());
        contentValues.put(DatabaseHelper.COL_MOMENT_IMAGE_LINK,imageMoment.getImage());

        long inserted = database.insert(DatabaseHelper.TABLE_EVENT_MOMENTS,null,contentValues);

        this.close();
        if(inserted>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean addEventExpense(Event event){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_EVENT_ID_FOREIGN, event.getId());
        contentValues.put(DatabaseHelper.COL_EXPENSE_SCOPE, event.getExpense_scope());
        contentValues.put(DatabaseHelper.COL_EXPENSE_AMOUNT, event.getExpense_amount());

        long inserted = database.insert(DatabaseHelper.TABLE_EVENT_EXPENSES,null,contentValues);

        this.close();
        if(inserted>0){
            return true;
        }else {
            return false;
        }
    }
    public User getUserData(String email){
        this.open();
        Cursor cursor =  database.query(DatabaseHelper.TABLE_USER,null,DatabaseHelper.COL_EMAIL+"=?",new String[]{email},null,null,null);
        cursor.moveToFirst();
        String fullName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_FULL_NAME));
        String imagePath=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_IMAGE));


        User user=new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setImage(imagePath);
        database.close();
        return  user;
    }

    public Event getEvent(int id){
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EVENT, new String[]{DatabaseHelper.COL_EVENT_ID, DatabaseHelper.COL_EVENT_NAME, DatabaseHelper.COL_EVENT_LOCATION, DatabaseHelper.COL_EVENT_START_DATE, DatabaseHelper.COL_EVENT_END_DATE, DatabaseHelper.COL_EVENT_BUDGET,DatabaseHelper.COL_EVENT_EXPENSES},DatabaseHelper.COL_EVENT_ID+" = "+id,null,null,null,null);

        cursor.moveToFirst();

            int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_ID));
            String mEventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
            String mEventLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_LOCATION));
            String mEventStartDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_START_DATE));
            String mEventEndDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_END_DATE));
            String mEventBudget = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_BUDGET));
            String mEventExpenses = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_EXPENSES));

            cursor.close();
            event=new Event(mId, mEventName,mEventLocation,mEventStartDate,mEventEndDate,mEventBudget,mEventExpenses);




        return event;

    }

    public ArrayList<Event> getExpensesByEvent(int event_id){
        ArrayList<Event>events = new ArrayList<>();
        this.open();

        Cursor cursor = database.rawQuery("SELECT A." + DatabaseHelper.COL_EVENT_EXPENSE_ID + ", B." + DatabaseHelper.COL_EVENT_NAME + ",A." + DatabaseHelper.COL_EXPENSE_SCOPE + ", A." + DatabaseHelper.COL_EXPENSE_AMOUNT + " FROM " + DatabaseHelper.TABLE_EVENT_EXPENSES + " as A Inner Join " + DatabaseHelper.TABLE_EVENT + " as B On A." + DatabaseHelper.COL_EVENT_ID_FOREIGN + "=B." + DatabaseHelper.COL_EVENT_ID + " Where A." + DatabaseHelper.COL_EVENT_ID_FOREIGN + " = " + event_id, null);

        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount();i++){
                int mExpenseId = cursor.getInt(0);
                String mEventName = cursor.getString(1);
                String mExpenseScope = cursor.getString(2);
                Double mExpenseAmount = cursor.getDouble(3);
                String mAmount = mExpenseAmount.toString();

                event=new Event(mExpenseId, mEventName, mExpenseScope, mAmount);

                events.add(event);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return events;
    }

    public ArrayList<Event>getAllEvents(){
        ArrayList<Event>events = new ArrayList<>();
        this.open();

        Cursor cursor = database.rawQuery("Select * From " + DatabaseHelper.TABLE_EVENT + " Order By " + DatabaseHelper.COL_EVENT_ID + " Desc", null);

        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount();i++){
                int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_ID));
                String mEventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
                String mEventLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_LOCATION));
                String mEventStartDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_START_DATE));
                String mEventEndDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_END_DATE));
                String mEventBudget = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_BUDGET));
                String mEventExpenses = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_EXPENSES));

                event=new Event(mId, mEventName,mEventLocation,mEventStartDate,mEventEndDate,mEventBudget,mEventExpenses);

                events.add(event);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return events;
    }

    public ArrayList<ImageMoment>getAllMomentsByEvent(int event_id){
        ArrayList<ImageMoment>imageMoments = new ArrayList<>();
        this.open();

        Cursor cursor = database.rawQuery("Select * From " + DatabaseHelper.TABLE_EVENT_MOMENTS + " Where "+DatabaseHelper.COL_EVENT_ID_FOREIGN_2+" = "+event_id+" Order By " + DatabaseHelper.COL_MOMENT_ID + " Desc", null);

        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount();i++){
                int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MOMENT_ID));
                int mEventId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_ID_FOREIGN_2));
                String mMomentName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MOMENT_NAME));
                String mMometDatTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MOMENT_DATE_TIME));
                byte[] mMomentImage = cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COL_MOMENT_IMAGE_LINK));

                imageMoment=new ImageMoment(mId, mEventId, mMomentName, mMomentImage, mMometDatTime);

                imageMoments.add(imageMoment);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return imageMoments;
    }

    public boolean updateEvent(int id,Event event){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_EVENT_NAME,event.getEvent_name());
        contentValues.put(DatabaseHelper.COL_EVENT_LOCATION,event.getEvent_location());
        contentValues.put(DatabaseHelper.COL_EVENT_START_DATE, event.getEvent_start_date());
        contentValues.put(DatabaseHelper.COL_EVENT_END_DATE, event.getEvent_end_date());
        contentValues.put(DatabaseHelper.COL_EVENT_BUDGET, event.getEvent_budget());

        int update=database.update(DatabaseHelper.TABLE_EVENT,contentValues,DatabaseHelper.COL_EVENT_ID+" = "+id, null);

        this.close();

        if (update > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateEventExpensing(int event_id,Event event){
        this.open();

//        return Double.parseDouble(event.getExpense_amount());

        String updateQuery = "UPDATE "+DatabaseHelper.TABLE_EVENT+" SET "+DatabaseHelper.COL_EVENT_EXPENSES+" = "+Double.parseDouble(event.getExpense_amount())+" + "+DatabaseHelper.COL_EVENT_EXPENSES+" WHERE "+DatabaseHelper.COL_EVENT_ID+" = "+event_id;

        Cursor c = database.rawQuery(updateQuery, null);



        if (c != null && c.getCount()>0){
            c.moveToFirst();
            c.close();
            this.close();
            return true;

        }else{
            return false;
        }
    }

    public boolean deleteEvent(int id){
        this.open();

        int deleted=database.delete(DatabaseHelper.TABLE_EVENT,DatabaseHelper.COL_EVENT_ID+" = "+id, null);

        this.close();
        if (deleted>0){
            return true;
        }else{
            return false;
        }
    }
}
