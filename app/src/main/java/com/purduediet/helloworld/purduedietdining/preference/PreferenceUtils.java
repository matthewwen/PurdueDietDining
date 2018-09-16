package com.purduediet.helloworld.purduedietdining.preference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.ContentProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.purduediet.helloworld.purduedietdining.database.DataMethod;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

public class PreferenceUtils {

    private static final String TAG = PreferenceUtils.class.getSimpleName();

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");

    //Tags for preferences
    private static final String CALORIES_TAG_LIMIT = "calories-tag-limit";
    private static final String IS_VEGAN_TAG = "is-vegan-tag";
    private static final String IS_GLUTEN_FREE_TAG = "is-gluten-free-tag";
    private static final String PROTEINS_TAG_LIMIT = "proteins-tag-limit";
    private static final String LAST_DATE_ADD_DATABASE = "last-date-add-database";

    //Default Values
    private static final int DEFAULT_CALORIES_COUNT = 1000;

    public static void setCaloriesLimit(Context context, int limit) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CALORIES_TAG_LIMIT, limit);
        editor.apply();
    }

    public static int getCaloriesLimit(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(CALORIES_TAG_LIMIT, DEFAULT_CALORIES_COUNT);
    }

    public static void setIsVegan(Context context, boolean val) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_VEGAN_TAG, val);
        editor.apply();

    }

    public static void setIsGlutenFree(Context context, boolean val) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_GLUTEN_FREE_TAG, val);
        editor.apply();

    }

    private static final int DEFAULT_PROTEINS_COUNT = 100;

    public static void setProteinsLimit(Context context, int limit) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PROTEINS_TAG_LIMIT, limit);
        editor.apply();
    }

    public static int getProteinsLimit(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(PROTEINS_TAG_LIMIT, DEFAULT_CALORIES_COUNT);
    }

    public static void setLastUpdateToday(Context context, int num){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(LAST_DATE_ADD_DATABASE, num);
        editor.apply();
    }

    public static boolean isUpdatedToday(Context context){
        long currentTime = DataMethod.getCurrentTime();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int updateTime = preferences.getInt(LAST_DATE_ADD_DATABASE, 33);
        int currentInt = Integer.parseInt(simpleDateFormat.format(currentTime));
        return currentInt == updateTime;
    }

}
