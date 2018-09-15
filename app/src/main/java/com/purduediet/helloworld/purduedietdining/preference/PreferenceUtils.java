package com.purduediet.helloworld.purduedietdining.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    private static final String TAG = PreferenceUtils.class.getSimpleName();

    //Tags for preferences
    private static final String CALORIES_TAG_LIMIT = "calories-tag-limit";

    //Default Values
    private static final int DEFAULT_CALORIES_COUNT = 1000;

    public static void setCaloriesLimit(Context context, int limit){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CALORIES_TAG_LIMIT, limit);
        editor.apply();
    }

    public static int getCaloriesLimit(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(CALORIES_TAG_LIMIT, 1000);
    }

}
