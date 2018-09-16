package com.purduediet.helloworld.purduedietdining.userInterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.purduediet.helloworld.purduedietdining.data.FoodData;
import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.adapter.MainAdapter;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;
import com.purduediet.helloworld.purduedietdining.preference.SettingsActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainAdapter.class.getSimpleName();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        switch (itemID){
            case R.id.action_calendar: {
                Intent intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_settings: {
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ItemFood> allItemFood = new ArrayList<>();

        final MainAdapter mAdapter = new MainAdapter(allItemFood);
        RecyclerView recyclerView = findViewById(R.id.main_view_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);


        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, ArrayList<ItemFood>> mAsyncTask = new AsyncTask<Void, Void, ArrayList<ItemFood>>(){

            @Override
            protected void onPostExecute(ArrayList<ItemFood> itemFoods) {
                Log.v(TAG, "Item Food size: " + itemFoods.size());
                mAdapter.setAllItems(itemFoods);
            }

            @Override
            protected ArrayList<ItemFood> doInBackground(Void... voids) {
                return FoodData.getAllData();
            }
        };

        mAsyncTask.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
