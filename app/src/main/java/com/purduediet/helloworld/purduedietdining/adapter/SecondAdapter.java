package com.purduediet.helloworld.purduedietdining.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.data.FoodData;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

import java.util.ArrayList;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.DataViewModel> {

    private ArrayList<ItemFood> allItems;

    public SecondAdapter(ArrayList<ItemFood> allItems){
        this.allItems = allItems;
    }

    @NonNull
    @Override
    public SecondAdapter.DataViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_item_recycle_view, viewGroup, false);
        return new SecondAdapter.DataViewModel(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SecondAdapter.DataViewModel viewModel, int i) {
        ItemFood food;
        food = allItems.get(i);
        viewModel.mNameTv.setText(food.getName());
        viewModel.mDescpritionTv.setText("Dining Court: " + FoodData.DINING_COURT[food.getDiningId()] + "| BLD: " + FoodData.BLD[food.getBreakLunchDinner()] + "| Station: " + food.getStation());
        viewModel.mCaloriesTv.setText(Integer.toString(food.getCalories()));
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    public void setAllItems(ArrayList<ItemFood> allItems){
        this.allItems = allItems;
        notifyDataSetChanged();
    }

    class DataViewModel extends RecyclerView.ViewHolder{

        TextView mNameTv;
        TextView mDescpritionTv;
        TextView mCaloriesTv;

        public DataViewModel(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.name_food_tv);
            mDescpritionTv = itemView.findViewById(R.id.station_food_tv);
            mCaloriesTv = itemView.findViewById(R.id.calories_food_tv);
        }
    }

}