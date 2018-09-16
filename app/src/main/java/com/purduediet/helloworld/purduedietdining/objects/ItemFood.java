package com.purduediet.helloworld.purduedietdining.objects;

public class ItemFood {

    int id;
    private int breakLunchDinner;
    int diningId;
    private String name;
    private String station;
    private int calories;
    private boolean eggs;
    private boolean fish;
    private boolean gluten;
    private boolean milk;
    private boolean peanuts;
    private boolean shellfish;
    private boolean soy;
    private boolean treeNuts;
    private boolean vegetarian;
    private boolean vegan;
    private boolean wheat;

    public ItemFood(String name){
        this.name = name;
    }

    public static int getType(String type){
        if (type.equals("Breakfast")){
            return 0;
        }else if (type.equals("Lunch")){
            return 1;
        }else {
            return 2;
        }
    }

    public ItemFood(int id, int diningId, int breakLunchDinner, String name, String station,
                    boolean eggs, boolean fish, boolean gluten,
                    boolean milk, boolean peanuts, boolean shellfish,
                    boolean soy, boolean treeNuts, boolean vegetarian,
                    boolean vegan, boolean wheat){
        this.id = id; this.breakLunchDinner = breakLunchDinner; this.diningId = diningId;
        this.name = name; this.station = station; this.calories = 150;
        this.eggs = eggs; this.fish = fish; this.gluten = gluten; this.milk = milk;
        this.peanuts = peanuts; this.eggs = peanuts; this.shellfish = shellfish; this.soy = soy;
        this.treeNuts = treeNuts; this.vegetarian = vegetarian; this.vegan = vegan; this.wheat = wheat;
    }

    public int getDiningId(){
        return diningId;
    }

    public int getBreakLunchDinner() {
        return breakLunchDinner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStation(){
        return station;
    }

    public int getCalories(){
        return calories;
    }

    public boolean isEggs() {
        return eggs;
    }

    public boolean isFish() {
        return fish;
    }

    public boolean isGluten() {
        return gluten;
    }

    public boolean isMilk() {
        return milk;
    }

    public boolean isPeanuts() {
        return peanuts;
    }

    public boolean isShellfish() {
        return shellfish;
    }

    public boolean isSoy() {
        return soy;
    }

    public boolean isTreeNuts() {
        return treeNuts;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public boolean isWheat() {
        return wheat;
    }
}
