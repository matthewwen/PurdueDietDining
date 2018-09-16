package com.purduediet.helloworld.purduedietdining.userInterface;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.adapter.SecondAdapter;
import com.purduediet.helloworld.purduedietdining.database.DataContract;
import com.purduediet.helloworld.purduedietdining.database.DataMethod;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = CalendarActivity.class.getSimpleName();

    //Tag for variables in bundle
    private static final String TIME_USER_SELECTED_TAG = "time-user-selected-tag";

    long currentTime;

    RecyclerView mRecycleView;
    SecondAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final Toolbar toolbar = findViewById(R.id.calendar_toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null){
            savedInstanceState = new Bundle();
            currentTime = DataMethod.getCurrentTime();
            savedInstanceState.putLong(TIME_USER_SELECTED_TAG, currentTime);
        }else {
            currentTime = savedInstanceState.getLong(TIME_USER_SELECTED_TAG);
        }

        FloatingActionButton fab = findViewById(R.id.calendar_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecycleView = findViewById(R.id.calendar_rv_duh);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        mAdapter = new SecondAdapter(new ArrayList<ItemFood>());
        mRecycleView.setLayoutManager(linearLayout);
        mRecycleView.setAdapter(mAdapter);

        //setting up the collapsing tool bar
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.calendar_toolbar_layout);
        final AppBarLayout appBarLayout = findViewById(R.id.calendar_app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + i == 0){
                    LinearLayout linearLayout = findViewById(R.id.activity_calendar_ll);
                    linearLayout.setVisibility(View.GONE);
                    collapsingToolbarLayout.setTitle(CalendarActivity.this.getString(R.string.app_name));
                    isShow = true;

                    ActionBar actionBar = getSupportActionBar();
                    if (actionBar != null){
                        actionBar.setDisplayHomeAsUpEnabled(true);
                    }
                }else if (isShow){
                    LinearLayout linearLayout = findViewById(R.id.activity_calendar_ll);
                    linearLayout.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle("");
                    toolbar.setTitle("");
                    isShow = false;

                    ActionBar actionBar = getSupportActionBar();
                    if (actionBar != null){
                        actionBar.setDisplayHomeAsUpEnabled(false);
                    }
                }
            }
        });
        appBarLayout.setExpanded(false);

        //Getting the Calendar from the action bar
        CalendarView calendarView = findViewById(R.id.calendar_cv);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                appBarLayout.setExpanded(false);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, date);
                currentTime = calendar.getTimeInMillis();
                updateView(calendar.getTimeInMillis());
            }
        });


    }

    private void updateView(long time){
        long getLowRange = DataMethod.getTimeLow(time);
        long getHighRange = DataMethod.getTimeHigh(time);
        ArrayList<ItemFood> allItems = new ArrayList<>();
        Cursor cursor = getContentResolver().query(DataContract.Food.EVENT_CONTENT_URI, DataContract.TodayFood.PROJECTION_ARRAY,
                DataContract.Food.COLUMN_TIME_ADDED + " =>? " , new String[]{
                Long.toString(getLowRange)}, null);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()){
            int id = cursor.getInt(DataContract.Food.COLUMN_ID_ARRAY_INDEX);
            long timeAdded = cursor.getLong(DataContract.Food.COLUMN_TIME_ADDED_ARRAY_INDEX);
            String name = cursor.getString(DataContract.Food.COLUMN_FOOD_NAME_ARRAY_INDEX);
            int diningID = cursor.getInt(DataContract.Food.COLUMN_LOCATION_ARRAY_INDEX);
            ItemFood temp = new ItemFood(id, timeAdded, diningID, name, 0, 0);
            allItems.add(temp);
        }
        cursor.close();
        mRecycleView.setAdapter(mAdapter);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong(TIME_USER_SELECTED_TAG, currentTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        currentTime = savedInstanceState.getLong(TIME_USER_SELECTED_TAG);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:{
                super.onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
