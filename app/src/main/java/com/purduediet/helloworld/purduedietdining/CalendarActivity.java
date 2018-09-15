package com.purduediet.helloworld.purduedietdining;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final Toolbar toolbar = findViewById(R.id.calendar_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.calendar_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //setting up the collapsing tool bar
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.calendar_toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.calendar_app_bar);
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
