package com.purduediet.helloworld.purduedietdining.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.purduediet.helloworld.purduedietdining.database.DataContract.Food;

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
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
