package com.purduediet.helloworld.purduedietdining.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

public class StickyRecyclerHeadersTouchListener implements RecyclerView.OnItemTouchListener{

    RecyclerView recyclerView; //the recycle view on the main page

    //constructor method
    StickyRecyclerHeadersTouchListener(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        //do nothing
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {
        //do nothing
    }
}
