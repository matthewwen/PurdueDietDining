package com.purduediet.helloworld.purduedietdining.database;

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


}
