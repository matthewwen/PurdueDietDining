package com.purduediet.helloworld.purduedietdining.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.purduediet.helloworld.purduedietdining.database.DataContract.Food;

public class DataHelper extends SQLiteOpenHelper {

    public static final String TAG = DataHelper.class.getSimpleName();

    private static final int VERSION = 1;

    public DataHelper(Context context) {
        super(context, TAG, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String USER_CREATE_TABLE = "CREATE TABLE " + Food.TABLE_NAME + " (" +
                Food._ID + " INTEGER PRIMARY KEY, " +
                Food.COLUMN_TIME_ADDED + " LONG NOT NULL, " +
                Food.COLUMN_FOOD_NAME + " TEXT NOT NULL, " +
                Food.COLUMN_LOCATION + "INTEGER NOT NULL," +
                Food.COLUMN_CALORIES + "INTEGER NOT NULL,"+
                Food.COLUMN_PROTEIN + "INTEGER NOT NULL);";
        db.execSQL(USER_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
