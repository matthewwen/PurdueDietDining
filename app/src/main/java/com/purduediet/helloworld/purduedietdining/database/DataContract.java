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

    public static class TodayFood implements BaseColumns{

        public static final String TABLE_NAME = "todayFood";

        //content uri with the appended path
        public static final Uri EVENT_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(PATH_DATA).appendPath(TABLE_NAME).build();

        //column name
        public static final String COLUMN_BLD = "breakLunchDinner";
        public static final String COLUMN_LOCATION ="locationEAT";
        public static final String COLUMN_FOOD_NAME = "foodNameEat";
        public static final String COLUMN_FOOD_STATION ="foodStation";
        public static final String COLUMN_FOOD_EGGS ="algEggs";
        public static final String COLUMN_FOOD_FISH ="algFish";
        public static final String COLUMN_FOOD_GLUTEN ="algGluent";
        public static final String COLUMN_FOOD_MILK ="algMilk";
        public static final String COLUMN_FOOD_PEANUTS ="algPeanuts";
        public static final String COLUMN_FOOD_SHELL_FISH ="algShellFish";
        public static final String COLUMN_FOOD_SOY ="algSoy";
        public static final String COLUMN_FOOD_TREE_NUTS ="algTreeNuts";
        public static final String COLUMN_FOOD_VEGETARIAN ="algVegetarian";
        public static final String COLUMN_FOOD_VEGAN ="algVegan";
        public static final String COLUMN_FOOD_WHEAT ="algWheat";

        //the array for the database
        public static final String[] PROJECTION_ARRAY = {
                TodayFood._ID,
                COLUMN_BLD,
                COLUMN_LOCATION,
                COLUMN_FOOD_NAME,
                COLUMN_FOOD_STATION,
                COLUMN_FOOD_EGGS,
                COLUMN_FOOD_FISH,
                COLUMN_FOOD_GLUTEN,
                COLUMN_FOOD_MILK,
                COLUMN_FOOD_PEANUTS,
                COLUMN_FOOD_SHELL_FISH,
                COLUMN_FOOD_SOY,
                COLUMN_FOOD_TREE_NUTS,
                COLUMN_FOOD_VEGETARIAN,
                COLUMN_FOOD_VEGAN,
                COLUMN_FOOD_WHEAT
        };

        //The int index
        public static final int COLUMN_ID_ARRAY_INDEX = 0;
        public static final int COLUMN_BLD_INDEX = 1;
        public static final int COLUMN_LOCATION_INDEX = 2;
        public static final int COLUMN_FOOD_NAME_INDEX = 3;
        public static final int COLUMN_FOOD_STATION_INDEX = 4;
        public static final int COLUMN_FOOD_EGGS_INDEX = 5;
        public static final int COLUMN_FOOD_FISH_INDEX = 6;
        public static final int COLUMN_FOOD_GLUTEN_INDEX = 7;
        public static final int COLUMN_FOOD_MILK_INDEX = 8;
        public static final int COLUMN_FOOD_PEANUTS_INDEX = 9;
        public static final int COLUMN_FOOD_SHELL_FISH_INDEX = 10;
        public static final int COLUMN_FOOD_SOY_INDEX = 11;
        public static final int COLUMN_FOOD_TREE_NUTS_INDEX = 12;
        public static final int COLUMN_FOOD_VEGETARIAN_INDEX = 13;
        public static final int COLUMN_FOOD_VEGAN_INDEX = 14;
        public static final int COLUMN_FOOD_WHEAT_INDEX = 15;

    }
}
