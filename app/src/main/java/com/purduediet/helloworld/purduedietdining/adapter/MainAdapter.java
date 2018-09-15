package com.purduediet.helloworld.purduedietdining.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.purduediet.helloworld.purduedietdining.R;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewModel> {

    ArrayList<ItemFood> allItems;

    public MainAdapter(ArrayList<ItemFood> allItems){
        this.allItems = allItems;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_item_recycle_view, viewGroup, false);
        return new ViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel viewModel, int i) {
        ItemFood food = allItems.get(i);
        viewModel.mTextView.setText(food.getName());
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    class ViewModel extends RecyclerView.ViewHolder{

        TextView mTextView;

        public ViewModel(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.name_food_tv);
        }
    }
}
