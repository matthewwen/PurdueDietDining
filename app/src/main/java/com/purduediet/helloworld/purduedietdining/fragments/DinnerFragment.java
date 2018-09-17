package com.purduediet.helloworld.purduedietdining.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.adapter.MainAdapter;
import com.purduediet.helloworld.purduedietdining.adapter.RecyclerSectionItemDecoration;
import com.purduediet.helloworld.purduedietdining.data.FoodData;
import com.purduediet.helloworld.purduedietdining.database.DataMethod;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;
import com.purduediet.helloworld.purduedietdining.userInterface.ItemFoodActivity;
import com.purduediet.helloworld.purduedietdining.userInterface.MainActivity;

import java.util.ArrayList;

public class DinnerFragment extends Fragment implements MainAdapter.MainActivityRecycleActivity{

    RecyclerView recyclerView; //the breakfast recycle view
    ArrayList<ItemFood> allItemFood; //All Breakfast options
    MainAdapter mAdapter; //the main adapter

    ArrayList<Integer> allItemsID;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Creating the View
        View view = inflater.inflate(R.layout.fragment_dinner, container, false);

        //Initializing global variables
        recyclerView = view.findViewById(R.id.dinner_rv);
        allItemFood = DataMethod.getDinnerTodayOptions(getContext());
        mAdapter = new MainAdapter(this, allItemFood);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),
                        true,
                        getSectionCallBack(allItemFood));
        recyclerView.addItemDecoration(sectionItemDecoration);

        return view;
    }

    @Override
    public void onClick(int id) {
        Intent intent = new Intent(getContext(), ItemFoodActivity.class);
        intent.putExtra(MainActivity.FOOD_ITEM_SELECTED_ID, id);
        startActivity(intent);
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
    public void onSelected(int id) {
        allItemsID.add(id);
    }

    @Override
    public void onDeSelected(int id) {
        allItemsID.remove(id);
    }
}
