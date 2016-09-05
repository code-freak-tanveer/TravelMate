package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Code Freak Tanveer on 13/08/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
private static final String DATABASE_NAME="tour_mate";
    static final String TABLE_USER="user";
    static final String TABLE_EVENT="event";
    static final String TABLE_EVENT_EXPENSES="event_expenses";
    static final String TABLE_EVENT_MOMENTS="event_moments";
    static final String COL_USER_NAME="user_name";
    static final String COL_PASSWORD="password";
    static final String COL_FULL_NAME="full_name";
    static final String COL_EMAIL="email";
    static final String COL_IMAGE="img_profile";

    private final String CREATE_TABLE_USER="CREATE TABLE "+TABLE_USER+" (" +
            COL_USER_NAME+" VARCHAR(50) PRIMARY KEY," +
            COL_PASSWORD+" VARCHAR(50)," +
            COL_EMAIL+" VARCHAR(50)," +
            COL_FULL_NAME+" VARCHAR(50)," +
            COL_IMAGE+" VARCHAR(255))";

    static final String COL_EVENT_ID="id";
    static final String COL_EVENT_NAME="event_name";
    static final String COL_EVENT_LOCATION="event_location";
    static final String COL_EVENT_START_DATE="event_start_date";
    static final String COL_EVENT_END_DATE="event_end_date";
    static final String COL_EVENT_BUDGET="event_budget";
    static final String COL_EVENT_EXPENSES="event_expenses";

    static final String CREATE_EVENT_TABLE="create table "+TABLE_EVENT+"( "+COL_EVENT_ID+" integer primary key, "+
            COL_EVENT_NAME+" text, "+COL_EVENT_LOCATION+" text, "+COL_EVENT_START_DATE+" text, "+
            COL_EVENT_END_DATE+" text, "+COL_EVENT_BUDGET+" REAL, "+COL_EVENT_EXPENSES+" REAL);";


    static final String COL_EVENT_EXPENSE_ID="id";
    static final String COL_EVENT_ID_FOREIGN="event_id";
    static final String COL_EXPENSE_SCOPE="expense_scope";
    static final String COL_EXPENSE_AMOUNT="expense_amount";

    static final String CREATE_EVENT_EXPENSE_TABLE="create table "+TABLE_EVENT_EXPENSES+"( "+COL_EVENT_EXPENSE_ID+" integer primary key, "+
            COL_EVENT_ID_FOREIGN+" integer, "+COL_EXPENSE_SCOPE+" text, "+COL_EXPENSE_AMOUNT+" REAL);";


    static final String COL_MOMENT_ID="id";
    static final String COL_EVENT_ID_FOREIGN_2="event_id";
    static final String COL_MOMENT_NAME="moment_name";
    static final String COL_MOMENT_IMAGE_LINK="moment_image_link";
    static final String COL_MOMENT_DATE_TIME="moment_date_time";

    static final String CREATE_EVENT_MOMENTS_TABLE="create table "+TABLE_EVENT_MOMENTS+"( "+COL_MOMENT_ID+" integer primary key, "+
            COL_EVENT_ID_FOREIGN_2+" integer, "+COL_MOMENT_NAME+" text, "+COL_MOMENT_DATE_TIME+" text, "+COL_MOMENT_IMAGE_LINK+" BLOB);";



    public DatabaseHelper(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_EVENT_TABLE);
        sqLiteDatabase.execSQL(CREATE_EVENT_EXPENSE_TABLE);
        sqLiteDatabase.execSQL(CREATE_EVENT_MOMENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exist"+TABLE_USER);
        sqLiteDatabase.execSQL("drop table if exist"+TABLE_EVENT);
        sqLiteDatabase.execSQL("drop table if exist"+CREATE_EVENT_EXPENSE_TABLE);
        sqLiteDatabase.execSQL("drop table if exist"+CREATE_EVENT_MOMENTS_TABLE);

        onCreate(sqLiteDatabase);

    }
}
