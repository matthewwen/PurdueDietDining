package com.purduediet.helloworld.purduedietdining.userInterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.purduediet.helloworld.purduedietdining.userInterface.CalendarActivity;
import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.adapter.MainAdapter;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;
import com.purduediet.helloworld.purduedietdining.preference.SettingsActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
        allItemFood.add(new ItemFood("Hamburger", "A Plain Burger", 310));
        allItemFood.add(new ItemFood("Pizza", "Cheese Pizza", 200));
        allItemFood.add(new ItemFood("Pasta", "Include Tomato Sauce", 500));

        MainAdapter mAdapter = new MainAdapter(allItemFood);
        RecyclerView recyclerView = findViewById(R.id.main_view_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
