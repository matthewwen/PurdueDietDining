package com.purduediet.helloworld.purduedietdining.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {

    private static final String BASE_CONTENT_URI = "com.purduediet.helloworld.purduedietdining";
    private static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI);

    class Food implements BaseColumns{
        public static final String tableName = "listFood";

        //column name
        public static final String COLUMN_TIME_ADDED = "timeEat";
        public static final String COLUMN_FOOD_NAME = "foodEat";

        //the array for the database
        public final String[] listArray = {
                Food._ID,
                COLUMN_TIME_ADDED,
                COLUMN_FOOD_NAME
        };

        //The int index
        public static final int COLUMN_ID_ARRAY_INDEX = 0;
        public static final int COLUMN_TIME_ADDED_ARRAY_INDEX = 1;
        public static final int COLUMN_FOOD_NAME_ARRAY_INDEX = 2;

    }
}
