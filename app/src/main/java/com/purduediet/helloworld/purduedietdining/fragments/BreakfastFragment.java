package com.purduediet.helloworld.purduedietdining.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.recyclerview.adapter.HeadingAdapter;
import com.purduediet.helloworld.purduedietdining.recyclerview.adapter.MainAdapter;
import com.purduediet.helloworld.purduedietdining.recyclerview.RecyclerSectionItemDecoration;
import com.purduediet.helloworld.purduedietdining.data.FoodData;
import com.purduediet.helloworld.purduedietdining.database.DataMethod;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

import java.util.ArrayList;

public class BreakfastFragment extends Fragment
        implements RecyclerSectionItemDecoration.OpenDiningActivity,
        HeadingAdapter.HeadingSetPreference{

    RecyclerView recyclerView; //the breakfast recycle view
    ArrayList<ItemFood> allItemFood; //All Breakfast options
    MainAdapter mAdapter; //the main adapter

    ArrayList<Integer> allItemsID;

    //creating the headings
    RecyclerSectionItemDecoration sectionItemDecoration;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Creating the View
        View view = inflater.inflate(R.layout.fragment_breakfast, container, false);

        //Initializing global variables
        recyclerView = view.findViewById(R.id.breakfast_rv);
        allItemFood = DataMethod.getBreakfastTodayOptions(getContext());
        mAdapter = new MainAdapter(allItemFood);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        sectionItemDecoration =
                new RecyclerSectionItemDecoration(this, getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),
                        true,
                        getSectionCallBack(allItemFood));
        recyclerView.addItemDecoration(sectionItemDecoration);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //The Headings
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
    public void showListOfDiningCourts() {
        mAdapter.clear();
        recyclerView.removeItemDecoration(sectionItemDecoration);
        HeadingAdapter adapter = new HeadingAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setUpDrive(int pos) {

    }
}
