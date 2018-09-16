package com.purduediet.helloworld.purduedietdining.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.purduediet.helloworld.purduedietdining.database.DataContract.Food;
import com.purduediet.helloworld.purduedietdining.database.DataContract.TodayFood;

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
                Food.COLUMN_LOCATION + " INTEGER NOT NULL, " +
                Food.COLUMN_CALORIES + " INTEGER NOT NULL, "+
                Food.COLUMN_PROTEIN + " INTEGER NOT NULL);";
        db.execSQL(USER_CREATE_TABLE);

        final String TODAY_MENU_CREATE_TABLE = "CREATE TABLE " + TodayFood.TABLE_NAME + " (" +
                TodayFood._ID + " INTEGER PRIMARY KEY, " +
                TodayFood.COLUMN_BLD + " LONG NOT NULL, " +
                TodayFood.COLUMN_LOCATION + " INTEGER NOT NULL, " +
                TodayFood.COLUMN_FOOD_NAME + " TEXT NOT NULL, " +
                TodayFood.COLUMN_FOOD_STATION + " TEXT NOT NULL, "+
                TodayFood.COLUMN_FOOD_EGGS + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_FISH + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_GLUTEN + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_MILK + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_PEANUTS + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_SHELL_FISH + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_SOY + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_TREE_NUTS + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_VEGETARIAN + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_VEGAN + " INTEGER NOT NULL, "+
                TodayFood.COLUMN_FOOD_WHEAT + " INTEGER NOT NULL);";
        db.execSQL(TODAY_MENU_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Food.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TodayFood.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
