package com.purduediet.helloworld.purduedietdining.objects;

public class ItemFood {

    private String name;
    private String description;
    private int calories;

    public ItemFood(String name){
        this.name = name;
    }

    public ItemFood(String name, String description, int calories){
        this.name = name; this.description = description; this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getCalories(){
        return calories;
    }
}
