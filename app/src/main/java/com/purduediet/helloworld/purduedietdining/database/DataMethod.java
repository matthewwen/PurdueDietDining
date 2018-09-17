package com.purduediet.helloworld.purduedietdining.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.purduediet.helloworld.purduedietdining.objects.ItemFood;
import com.purduediet.helloworld.purduedietdining.database.DataContract.Food;
import com.purduediet.helloworld.purduedietdining.database.DataContract.TodayFood;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DataMethod {

    //amount of millisecond in a second
    private static final long NUM_MILI_SEC = TimeUnit.MINUTES.toMillis(1);
    private static final long DAY_TO_MIL_SEC = TimeUnit.DAYS.toMillis(1);
    private static final long LONDON_TO_PURDUE = TimeUnit.HOURS.toMillis(4);

    public static long getCurrentTime(){
        long currentTime = Calendar.getInstance().getTimeInMillis();
        return currentTime - currentTime % NUM_MILI_SEC;
    }

    public static long getTimeLow(long time){
        long currentTime = time - time % NUM_MILI_SEC;
        long lowRange = currentTime - currentTime % DAY_TO_MIL_SEC;
        lowRange += LONDON_TO_PURDUE + 1;
        return lowRange;
    }

    public static long getTimeHigh(long time){
        long currentTime = time - time % NUM_MILI_SEC;
        long highRange = currentTime - currentTime % DAY_TO_MIL_SEC;
        highRange += LONDON_TO_PURDUE + DAY_TO_MIL_SEC - 1;
        return highRange;
    }

    public static void addUserDatabaseItem(Context context, ItemFood itemFood){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Food.COLUMN_TIME_ADDED, getCurrentTime());
        contentValues.put(Food.COLUMN_FOOD_NAME, itemFood.getName());
        contentValues.put(Food.COLUMN_LOCATION, itemFood.getDiningId());
        contentValues.put(Food.COLUMN_CALORIES, itemFood.getCalories());
        contentValues.put(Food.COLUMN_PROTEIN, itemFood.getProtein());
        Uri uri = Food.EVENT_CONTENT_URI; 
        context.getContentResolver().insert(uri, contentValues);
    }

    public static void addTodayDatabaseItem(Context context, ItemFood itemFood){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodayFood.COLUMN_BLD, itemFood.getBreakLunchDinner());
        contentValues.put(TodayFood.COLUMN_LOCATION, itemFood.getDiningId());
        contentValues.put(TodayFood.COLUMN_FOOD_NAME, itemFood.getName());
        contentValues.put(TodayFood.COLUMN_FOOD_STATION, itemFood.getStation());
        contentValues.put(TodayFood.COLUMN_FOOD_EGGS, getBoolToIntVal(itemFood.isEggs()));
        contentValues.put(TodayFood.COLUMN_FOOD_FISH, getBoolToIntVal(itemFood.isFish()));
        contentValues.put(TodayFood.COLUMN_FOOD_GLUTEN, getBoolToIntVal(itemFood.isGluten()));
        contentValues.put(TodayFood.COLUMN_FOOD_MILK, getBoolToIntVal(itemFood.isMilk()));
        contentValues.put(TodayFood.COLUMN_FOOD_PEANUTS, getBoolToIntVal(itemFood.isPeanuts()));
        contentValues.put(TodayFood.COLUMN_FOOD_SHELL_FISH, getBoolToIntVal(itemFood.isShellfish()));
        contentValues.put(TodayFood.COLUMN_FOOD_SOY, getBoolToIntVal(itemFood.isSoy()));
        contentValues.put(TodayFood.COLUMN_FOOD_TREE_NUTS, getBoolToIntVal(itemFood.isTreeNuts()));
        contentValues.put(TodayFood.COLUMN_FOOD_VEGETARIAN, getBoolToIntVal(itemFood.isVegetarian()));
        contentValues.put(TodayFood.COLUMN_FOOD_VEGAN, getBoolToIntVal(itemFood.isVegan()));
        contentValues.put(TodayFood.COLUMN_FOOD_WHEAT, getBoolToIntVal(itemFood.isWheat()));

        Uri uri = TodayFood.EVENT_CONTENT_URI;
        Uri netUri = context.getContentResolver().insert(uri, contentValues);
    }

    private static int getBoolToIntVal(boolean value){
        if (value){
            return 1;
        }
        return 0;
    }

    private static boolean getIntToBoolVal(int val){
        return val == 1;
    }


    public static void addDatabaseListToDatabase(Context context, ArrayList<ItemFood> allFood){
        for (int i = 0; i < allFood.size(); i++){
            addUserDatabaseItem(context, allFood.get(i));
        }
    }

    public static void addTodayMenuToDatabase(Context context, ArrayList<ItemFood> allFood){
        for (int i = 0; i < allFood.size(); i++){
            addTodayDatabaseItem(context, allFood.get(i));
        }
    }

    public static ArrayList<ItemFood> getTodayMenuDatabase(Context context){
        ArrayList<ItemFood> allItems = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(TodayFood.EVENT_CONTENT_URI, TodayFood.PROJECTION_ARRAY,
                null, null, null);
        assert cursor != null;
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            int id = cursor.getInt(TodayFood.COLUMN_ID_ARRAY_INDEX);
            int bld = cursor.getInt(TodayFood.COLUMN_BLD_INDEX);
            int location = cursor.getInt(TodayFood.COLUMN_LOCATION_INDEX);
            String name = cursor.getString(TodayFood.COLUMN_FOOD_NAME_INDEX);
            String station = cursor.getString(TodayFood.COLUMN_FOOD_STATION_INDEX);
            int egg = cursor.getInt(TodayFood.COLUMN_FOOD_EGGS_INDEX);
            int fish = cursor.getInt(TodayFood.COLUMN_FOOD_FISH_INDEX);
            int fluten = cursor.getInt(TodayFood.COLUMN_FOOD_GLUTEN_INDEX);
            int milk = cursor.getInt(TodayFood.COLUMN_FOOD_MILK_INDEX);
            int peanuts = cursor.getInt(TodayFood.COLUMN_FOOD_PEANUTS_INDEX);
            int shellFish = cursor.getInt(TodayFood.COLUMN_FOOD_SHELL_FISH_INDEX);
            int soy = cursor.getInt(TodayFood.COLUMN_FOOD_SOY_INDEX);
            int treeNuts = cursor.getInt(TodayFood.COLUMN_FOOD_TREE_NUTS_INDEX);
            int vegetarian = cursor.getInt(TodayFood.COLUMN_FOOD_VEGETARIAN_INDEX);
            int vegan = cursor.getInt(TodayFood.COLUMN_FOOD_VEGAN_INDEX);
            int wheat = cursor.getInt(TodayFood.COLUMN_FOOD_WHEAT_INDEX);

            ItemFood temp = new ItemFood(id, location, bld, name, station, getIntToBoolVal(egg)
                    , getIntToBoolVal(fish) , getIntToBoolVal(fluten) , getIntToBoolVal(milk) , getIntToBoolVal(peanuts)
                    , getIntToBoolVal(shellFish) , getIntToBoolVal(soy) , getIntToBoolVal(treeNuts) , getIntToBoolVal(vegetarian)
                    , getIntToBoolVal(vegan) , getIntToBoolVal(wheat));
            allItems.add(temp);
        }
        cursor.close();
        return allItems;
    }

}
