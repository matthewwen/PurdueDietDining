package com.purduediet.helloworld.purduedietdining.database;

import android.content.Context;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DataMethod {

    //amount of milisecond in a second
    private static final long NUM_MILI_SEC = TimeUnit.MINUTES.toMillis(1);

    public static long getCurrentTime(Context context){
        long currentTime = Calendar.getInstance().getTimeInMillis();
        return currentTime - currentTime % NUM_MILI_SEC; 
    }
}
