package com.purduediet.helloworld.purduedietdining.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.purduediet.helloworld.purduedietdining.database.DataContract.Food;

public class DataHelper extends SQLiteOpenHelper {

    public DataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String USER_CREATE_TABLE = "CREATE TABLE " + Food.TABLE_NAME + " (" +
                Food._ID + " INTEGER PRIMARY KEY, " +
                Food.COLUMN_TIME_ADDED + " LONG NOT NULL, " +
                Food.COLUMN_FOOD_NAME + " TEXT NOT NULL, " +
                Food.COLUMN_DINING_COURT + "TEXT NOT NULL," +
                Food.COLUMN_LOCATION + "TEXT NOT NULL," +
                Food.COLUMN_MEAL_TYPE + "TEXT NOT NULL,"+
                Food.COLUMN_CALORIES + "INTEGERS NOT NULL,"+
                Food.COLUMN_PROTEIN + "INTEGERS NOT NULL);";
        db.execSQL(USER_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
