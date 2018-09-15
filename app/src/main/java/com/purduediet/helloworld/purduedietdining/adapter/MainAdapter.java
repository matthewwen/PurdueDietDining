package com.purduediet.helloworld.purduedietdining.adapter;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewModel> {

    private ArrayList<ItemFood> allItems;

    public MainAdapter(ArrayList<ItemFood> allItems){
        this.allItems = allItems;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_item_recycle_view, viewGroup, false);
        return new ViewModel(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewModel viewModel, int i) {
        ItemFood food;
        food = allItems.get(i);
        viewModel.mNameTv.setText(food.getName());
        viewModel.mDescpritionTv.setText(food.getDescription());
        viewModel.mCaloriesTv.setText(Integer.toString(food.getCalories()));
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    class ViewModel extends RecyclerView.ViewHolder{

        TextView mNameTv;
        TextView mDescpritionTv;
        TextView mCaloriesTv;

        public ViewModel(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.name_food_tv);
            mDescpritionTv = itemView.findViewById(R.id.description_food_tv);
            mCaloriesTv = itemView.findViewById(R.id.calories_food_tv);
        }
    }
}
