package com.purduediet.helloworld.purduedietdining.database;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.purduediet.helloworld.purduedietdining.objects.ItemFood;
import com.purduediet.helloworld.purduedietdining.database.DataContract.Food;


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

    public static void addItem(Context context, ItemFood itemFood){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Food.COLUMN_TIME_ADDED, getCurrentTime());
        contentValues.put(Food.COLUMN_FOOD_NAME, itemFood.getName());
        contentValues.put(Food.COLUMN_LOCATION, itemFood.getDiningId());
        contentValues.put(Food.COLUMN_CALORIES, itemFood.getCalories());
        contentValues.put(Food.COLUMN_PROTEIN, itemFood.getProtein());
        Uri uri = Food.EVENT_CONTENT_URI; 
        context.getContentResolver().update(uri, contentValues, null, null);
    }


}
