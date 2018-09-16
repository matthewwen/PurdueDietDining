package com.purduediet.helloworld.purduedietdining.objects;

public class ItemFood {

    private String diningCourt;
    private String timeOfDay;
    private String location;
    private String name;
    private int calories;
    private int protein;
    private boolean isVegetarian;
    private boolean isGlutenFree;

    public ItemFood(String name){
        this.name = name;
    }

    public ItemFood(String diningCourt, String timeOfDay, String location, String name, int calories, int protein, boolean isVegetarian, boolean isGlutenFree){
        this.diningCourt = diningCourt; this.timeOfDay = timeOfDay; this.location = location;
        this.name = name; this.calories = calories; this.protein = protein; this.isVegetarian = isVegetarian; this.isGlutenFree = isGlutenFree;
    }

    public String getName() {
        return name;
    }

    public int getProtein(){
        return protein;
    }

    public int getCalories(){
        return calories;
    }


}
