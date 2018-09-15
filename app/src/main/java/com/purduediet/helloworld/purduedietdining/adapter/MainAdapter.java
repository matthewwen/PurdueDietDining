package com.purduediet.helloworld.purduedietdining.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewModel> {

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel viewModel, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewModel extends RecyclerView.ViewHolder{

        public ViewModel(@NonNull View itemView) {
            super(itemView);
        }
    }
}
