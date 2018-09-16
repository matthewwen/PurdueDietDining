package com.purduediet.helloworld.purduedietdining.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {

    private static final String BASE_CONTENT_URI = "com.purduediet.helloworld.purduedietdining";
    private static final Uri CONTENT_URI = Uri.parse(BASE_CONTENT_URI);

    class Food implements BaseColumns{
        public static final String TABLE_NAME = "listFood";

        //column name
        public static final String COLUMN_TIME_ADDED = "timeEat";
        public static final String COLUMN_FOOD_NAME = "foodNameEat";
        public static final String COLUMN_DINING_COURT ="diningCourtEat";
        public static final String COLUMN_LOCATION ="locationEAT";
        public static final String COLUMN_MEAL_TYPE = "mealTypeEat";
        public static final String COLUMN_CALORIES ="caloriesEat";
        public static final String COLUMN_PROTEIN ="proteinEat";


        //the array for the database
        public final String[] listArray = {
                Food._ID,
                COLUMN_TIME_ADDED,
                COLUMN_FOOD_NAME,
                COLUMN_DINING_COURT,
                COLUMN_LOCATION,
                COLUMN_MEAL_TYPE,
                COLUMN_CALORIES,
                COLUMN_PROTEIN
        };

        //The int index
        public static final int COLUMN_ID_ARRAY_INDEX = 0;
        public static final int COLUMN_TIME_ADDED_ARRAY_INDEX = 1;
        public static final int COLUMN_FOOD_NAME_ARRAY_INDEX = 2;
        public static final int COLUMN_DINING_COURT_ARRAY_INDEX = 3;
        public static final int COLUMN_LOCATION_ARRAY_INDEX = 4;
        public static final int COLUMN_MEAL_TYPE_ARRAY_INDEX = 5;
        public static final int COLUMN_CALORIES_ARRAY_INDEX = 6;
        public static final int COLUMN_PROTEINS_ARRAY_INDEX = 7;






    }
}
