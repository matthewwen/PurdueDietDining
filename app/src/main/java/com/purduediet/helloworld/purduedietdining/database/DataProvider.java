package com.purduediet.helloworld.purduedietdining.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.purduediet.helloworld.purduedietdining.database.DataContract.Food;
import com.purduediet.helloworld.purduedietdining.database.DataContract.TodayFood;

import java.util.Objects;

public class DataProvider extends ContentProvider {

    //this is your database
    private DataHelper mDbHelper;

    //from the table add meal
    public static final int TABLE_USER_ADD_MEAL = 100;
    public static final int TABLE_USER_ADD_MEAL_ID = 101;

    public static final int TABLE_TODAY_MEAL = 200;
    public static final int TABLE_TODAY_MEAL_ID = 201;

    //this i the uri matcher
    private static final UriMatcher sUriMatcher = buildUriMatch();

    private static UriMatcher buildUriMatch() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(DataContract.AUTHORITY, DataContract.PATH_DATA + "/" + Food.TABLE_NAME, TABLE_USER_ADD_MEAL);
        uriMatcher.addURI(DataContract.AUTHORITY, DataContract.PATH_DATA + "/" + Food.TABLE_NAME +"/#", TABLE_USER_ADD_MEAL_ID);

        uriMatcher.addURI(DataContract.AUTHORITY, DataContract.PATH_DATA + "/" + TodayFood.TABLE_NAME, TABLE_TODAY_MEAL);
        uriMatcher.addURI(DataContract.AUTHORITY, DataContract.PATH_DATA + "/" + TodayFood.TABLE_NAME + "/#",TABLE_TODAY_MEAL_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DataHelper(getContext());
        return false;
    }

    public Cursor rawQuery(long lowTime, long highTime){
        SQLiteDatabase sqLiteDatabase = mDbHelper.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + Food.TABLE_NAME + "WHERE " + Food.COLUMN_TIME_ADDED + " BETWEEN " + (lowTime) + " AND " + highTime, null );
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqLiteDatabase = mDbHelper.getReadableDatabase();

        int id = sUriMatcher.match(uri);
        switch (id){
            case TABLE_USER_ADD_MEAL: {
                Cursor cursor = sqLiteDatabase.query(Food.TABLE_NAME, projection, selection, selectionArgs, null, null,  sortOrder);
                ContentResolver resolver = Objects.requireNonNull(getContext()).getContentResolver();
                if (resolver == null) return null;
                cursor.setNotificationUri(resolver, uri);
                return cursor;
            }
            case TABLE_USER_ADD_MEAL_ID: {
                selection = Food._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Cursor cursor = sqLiteDatabase.query(Food.TABLE_NAME, projection, selection, selectionArgs, null, null,  sortOrder);
                ContentResolver resolver = Objects.requireNonNull(getContext()).getContentResolver();
                if (resolver == null) return null;
                cursor.setNotificationUri(resolver, uri);
                return cursor;
            }
            case TABLE_TODAY_MEAL: {
                Cursor cursor = sqLiteDatabase.query(TodayFood.TABLE_NAME, projection, selection, selectionArgs, null, null,  sortOrder);
                ContentResolver resolver = Objects.requireNonNull(getContext()).getContentResolver();
                if (resolver == null) return null;
                cursor.setNotificationUri(resolver, uri);
                return cursor;
            }
            case TABLE_TODAY_MEAL_ID: {
                selection = TodayFood._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Cursor cursor = sqLiteDatabase.query(TodayFood.TABLE_NAME, projection, selection, selectionArgs, null, null,  sortOrder);
                ContentResolver resolver = Objects.requireNonNull(getContext()).getContentResolver();
                if (resolver == null) return null;
                cursor.setNotificationUri(resolver, uri);
                return cursor;
            }
        }
        return null;
    }



    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int id = sUriMatcher.match(uri);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        switch (id){
            case TABLE_USER_ADD_MEAL: {
                long theID = db.insert(Food.TABLE_NAME, null, values);
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, theID);
            }
            case TABLE_TODAY_MEAL: {
                long theID = db.insert(TodayFood.TABLE_NAME, null, values);

                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, theID);
            }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int id = sUriMatcher.match(uri);
        switch (id){
            case TABLE_USER_ADD_MEAL: {
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().delete(Food.TABLE_NAME, null, null);
            }
            case TABLE_USER_ADD_MEAL_ID:{
                selection = Food._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().delete(Food.TABLE_NAME, selection, selectionArgs);
            }
            case TABLE_TODAY_MEAL:{
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().delete(TodayFood.TABLE_NAME, selection, selectionArgs);
            }
            case TABLE_TODAY_MEAL_ID: {
                selection = TodayFood._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().delete(TodayFood.TABLE_NAME, selection, selectionArgs);
            }
        }
        return -1;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int id = sUriMatcher.match(uri);
        switch (id){
            case TABLE_USER_ADD_MEAL: {
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().update(Food.TABLE_NAME, values, null, null);
            }
            case TABLE_USER_ADD_MEAL_ID:{
                selection = Food._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().update(Food.TABLE_NAME, values, selection, selectionArgs);
            }
            case TABLE_TODAY_MEAL:{
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().update(TodayFood.TABLE_NAME, values,  selection, selectionArgs);
            }
            case TABLE_TODAY_MEAL_ID: {
                selection = TodayFood._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().update(TodayFood.TABLE_NAME, values, selection, selectionArgs);
            }
        }
        return -1;
    }
}
