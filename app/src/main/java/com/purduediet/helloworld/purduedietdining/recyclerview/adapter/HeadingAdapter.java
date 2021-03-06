package com.purduediet.helloworld.purduedietdining.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.purduediet.helloworld.purduedietdining.R;

public class HeadingAdapter extends RecyclerView.Adapter<HeadingAdapter.HeadingViewModel> {

    HeadingSetPreference context;

    //constructor method
    public HeadingAdapter(HeadingSetPreference context){
        this.context = context;
    }

    private static final String[] selectDiningArray = new String[]{
            "View All",
            "Earhart",
            "Ford",
            "Hillenbrand",
            "Wiley",
            "Windsor"
    };

    @NonNull
    @Override
    public HeadingViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selection_dining_court_recycle_view, viewGroup, false);
        return new HeadingViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadingViewModel headingViewModel, final int i) {
        headingViewModel.mHeading.setText(selectDiningArray[i]);
        headingViewModel.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.setUpDrive(i - 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectDiningArray.length;
    }

    class HeadingViewModel extends RecyclerView.ViewHolder{

        TextView mHeading;
        View view;

        HeadingViewModel(@NonNull View itemView) {
            super(itemView);
            mHeading = itemView.findViewById(R.id.select_heading_tv);
            view = itemView;
        }
    }

    public interface HeadingSetPreference{
        void setUpDrive(int pos);
    }
}
