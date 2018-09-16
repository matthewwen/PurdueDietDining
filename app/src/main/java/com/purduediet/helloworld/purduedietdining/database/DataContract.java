package com.purduediet.helloworld.purduedietdining.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {

    public static final String AUTHORITY = "com.purduediet.helloworld.purduedietdining";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    //name of the table
    public static final String PATH_DATA = "userData";

    public static class Food implements BaseColumns{

        public static final String TABLE_NAME = "listFood";

        //content uri with the appended path
        public static final Uri EVENT_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(PATH_DATA).appendPath(TABLE_NAME).build();

        //column name
        public static final String COLUMN_TIME_ADDED = "timeEat";
        public static final String COLUMN_FOOD_NAME = "foodNameEat";
        public static final String COLUMN_LOCATION ="locationEAT";
        public static final String COLUMN_CALORIES ="caloriesEat";
        public static final String COLUMN_PROTEIN ="proteinEat";


        //the array for the database
        public final String[] listArray = {
                Food._ID,
                COLUMN_TIME_ADDED,
                COLUMN_FOOD_NAME,
                COLUMN_LOCATION,
                COLUMN_CALORIES,
                COLUMN_PROTEIN
        };

        //The int index
        public static final int COLUMN_ID_ARRAY_INDEX = 0;
        public static final int COLUMN_TIME_ADDED_ARRAY_INDEX = 1;
        public static final int COLUMN_FOOD_NAME_ARRAY_INDEX = 2;
        public static final int COLUMN_LOCATION_ARRAY_INDEX = 3;
        public static final int COLUMN_CALORIES_ARRAY_INDEX = 4;
        public static final int COLUMN_PROTEINS_ARRAY_INDEX = 5;






    }
}
