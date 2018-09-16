package com.purduediet.helloworld.purduedietdining.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.purduediet.helloworld.purduedietdining.database.DataContract.Food;

import java.util.Objects;

public class DataProvider extends ContentProvider {

    //this is your database
    private DataHelper mDbHelper;

    //from the table add meal
    public static final int TABLE_ADD_MEAL = 100;

    //this i the uri matcher
    private static final UriMatcher sUriMatcher = buildUriMatch();

    private static UriMatcher buildUriMatch() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(DataContract.AUTHORITY, DataContract.PATH_DATA + "/" + Food.TABLE_NAME, TABLE_ADD_MEAL);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DataHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int id = sUriMatcher.match(uri);
        switch (id){
            case TABLE_ADD_MEAL: {
                SQLiteDatabase sqLiteDatabase = mDbHelper.getReadableDatabase();
                Cursor cursor = sqLiteDatabase.query(Food.TABLE_NAME, projection, selection, selectionArgs, null, null,  sortOrder);
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
        switch (id){
            case TABLE_ADD_MEAL: {
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                long theID = db.insert(Food.TABLE_NAME, null, values);
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
            case TABLE_ADD_MEAL: {
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return mDbHelper.getWritableDatabase().delete(Food.TABLE_NAME, null, null);
            }
        }
        return -1;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int id = sUriMatcher.match(uri);
        switch (id){
            case TABLE_ADD_MEAL:
                selection = Food._ID + " =?";
                selectionArgs = new String[]{Long.toString(ContentUris.parseId(uri))};
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                int number = db.update(Food.TABLE_NAME, values, selection, selectionArgs);
                Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
                return number;
        }
        return 0;
    }
}
