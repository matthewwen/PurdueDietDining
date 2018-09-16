package com.purduediet.helloworld.purduedietdining.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.data.FoodData;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewModel> {

    private ArrayList<ItemFood> allItems;
    boolean showTextBox;
    MainActivityRecycleActivity context;

    public MainAdapter(MainActivityRecycleActivity context, ArrayList<ItemFood> allItems){
        showTextBox = false;
        this.allItems = allItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_item_recycle_view, viewGroup, false);
        return new ViewModel(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewModel viewModel, final int i) {
        ItemFood food;
        food = allItems.get(i);
        viewModel.mNameTv.setText(food.getName());
        viewModel.mDescpritionTv.setText(FoodData.BLD[food.getBreakLunchDinner()] + "| Station: " + food.getStation());
        viewModel.mCaloriesTv.setText("");
        if (showTextBox){
            viewModel.mCheckBox.setVisibility(View.VISIBLE);
            viewModel.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        context.onSelected(i);
                    }else {
                        context.onDeSelected(i);
                    }
                }
            });
            viewModel.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }else {
            viewModel.mCheckBox.setVisibility(View.GONE);
            viewModel.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onClick(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    public void setAllItems(ArrayList<ItemFood> allItems){
        this.allItems = allItems;
        notifyDataSetChanged();
    }

    class ViewModel extends RecyclerView.ViewHolder{

        TextView mNameTv;
        TextView mDescpritionTv;
        TextView mCaloriesTv;
        CheckBox mCheckBox;
        View itemView;

        public ViewModel(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.name_food_tv);
            mDescpritionTv = itemView.findViewById(R.id.station_food_tv);
            mCaloriesTv = itemView.findViewById(R.id.calories_food_tv);
            mCheckBox = itemView.findViewById(R.id.main_view_cb);
            this.itemView = itemView;
        }
    }

    public void setCheckBoxVisible(boolean val){
        showTextBox = val;
        notifyDataSetChanged();
    }

    public boolean getCheckBoxVisible(){
        return showTextBox;
    }

    public interface MainActivityRecycleActivity{
        public void onClick(int id);
        public void onSelected(int id);
        public void onDeSelected(int id);
    }
}
