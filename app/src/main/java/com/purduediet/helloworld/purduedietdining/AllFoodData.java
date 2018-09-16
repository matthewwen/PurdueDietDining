package com.purduediet.helloworld.purduedietdining;

import android.arch.lifecycle.ViewModel;

import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

import java.util.ArrayList;

public class AllFoodData{

    private static ArrayList<ItemFood> allItems;

    public static void setAllItems(ArrayList<ItemFood> allItems){
        AllFoodData.allItems = allItems;
    }

    public static ArrayList<ItemFood> getAllitems(){
        return allItems;
    }
}
