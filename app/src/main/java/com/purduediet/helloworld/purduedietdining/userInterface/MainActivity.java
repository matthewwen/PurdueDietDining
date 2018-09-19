package com.purduediet.helloworld.purduedietdining.userInterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.purduediet.helloworld.purduedietdining.recyclerview.RecyclerSectionItemDecoration;
import com.purduediet.helloworld.purduedietdining.data.FoodData;
import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.recyclerview.adapter.MainAdapter;
import com.purduediet.helloworld.purduedietdining.database.DataContract;
import com.purduediet.helloworld.purduedietdining.database.DataMethod;
import com.purduediet.helloworld.purduedietdining.fragments.FragmentPageAdapter;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;
import com.purduediet.helloworld.purduedietdining.preference.PreferenceUtils;
import com.purduediet.helloworld.purduedietdining.preference.SettingsActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainActivityRecycleActivity {

    private static final String TAG = MainAdapter.class.getSimpleName();

    ArrayList<Integer> allItemsID;

    public static final String FOOD_ITEM_SELECTED_ID = "food-item-selected-id";

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
            case R.id.action_add: {
                //Do something
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allItemsID = new ArrayList<>();

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, ArrayList<ItemFood>> mAsyncTask = new AsyncTask<Void, Void, ArrayList<ItemFood>>(){

            @Override
            protected void onPostExecute(ArrayList<ItemFood> itemFoods) {
                Log.v(TAG, "Item Food size: " + itemFoods.size());
                MainActivity.this.getContentResolver().delete(DataContract.TodayFood.EVENT_CONTENT_URI, null, null);
                DataMethod.addTodayMenuToDatabase(MainActivity.this, itemFoods);
                int updateInt = Integer.parseInt(PreferenceUtils.simpleDateFormat.format(DataMethod.getCurrentTime()));
                PreferenceUtils.setLastUpdateToday(MainActivity.this, updateInt);
            }

            @Override
            protected ArrayList<ItemFood> doInBackground(Void... voids) {
                return FoodData.getAllData();
            }
        };
        if (!PreferenceUtils.isUpdatedToday(this)) {
            mAsyncTask.execute();
        }

        //Find the View Pager
        ViewPager pager = findViewById(R.id.bre_lunc_dinn_vp);
        FragmentPagerAdapter pagerAdapter = new FragmentPageAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        //The Tab Layout
        TabLayout tabLayout = findViewById(R.id.bre_lunc_dinn_tb);
        int[] color = {getResources().getColor(R.color.main_activity_tab_Indicator_color),
                getResources().getColor(R.color.main_activity_tab_selected_text_color),
                getResources().getColor(R.color.main_activity_tab_not_selected_text_color)};
        tabLayout.setSelectedTabIndicatorColor(color[0]);
        tabLayout.setTabTextColors(color[2], color[1]);

        tabLayout.setupWithViewPager(pager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallBack(final ArrayList<ItemFood> allItemFood){
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0 || (allItemFood.size() != 0 && allItemFood.get(position)
                        .getDiningId() != allItemFood.get(position - 1)
                        .getDiningId());
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                if (allItemFood.size() == 0){
                    return FoodData.DINING_COURT[0];
                }
                int diningID = allItemFood.get(position).getDiningId();
                return FoodData.DINING_COURT[diningID];
            }
        };
    }

    @Override
    public void onClick(int id) {
        Intent intent = new Intent(this, ItemFoodActivity.class);
        intent.putExtra(FOOD_ITEM_SELECTED_ID, id);
        startActivity(intent);
    }

    @Override
    public void onSelected(int id) {
        allItemsID.add(id);
    }

    @Override
    public void onDeSelected(int id) {
        allItemsID.remove(id);
    }

    public ArrayList<ItemFood> getAllSelectedItems(ArrayList<ItemFood> allItemFood, ArrayList<Integer> allItemsID) {
        ArrayList<ItemFood> selectedFood = new ArrayList<>();
        for (int i = 0; i < allItemFood.size(); i++){
            for (Integer val : allItemsID){
                if (allItemFood.get(i).getId() == val){
                    selectedFood.add(allItemFood.get(i));
                    break;
                }
            }
        }
        return selectedFood;
    }
}
