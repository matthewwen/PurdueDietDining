package com.purduediet.helloworld.purduedietdining.objects;

public class ItemFood {

    int id; //the id inside the database
    private int breakLunchDinner; //served breakfast, lunch, or dinner
    long dateEntered; //date entered
    int diningId; //which dining court is it in
    int protein; //amount of protein inside the food
    private String name; //name of the food
    private String station; //which station food is in
    private int calories; //amount of calories
    private boolean eggs; //allergies
    private boolean fish; //allergies
    private boolean gluten; //allergies
    private boolean milk; //allergies
    private boolean peanuts; //allergies
    private boolean shellfish; //allergies
    private boolean soy; //allergies
    private boolean treeNuts; //allergies
    private boolean vegetarian; //allergies
    private boolean vegan; //allergies
    private boolean wheat; //allergies

    private boolean isSelected; //it is selected as an eaten meal

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

    public ItemFood(int id, long dateEntered, int diningId, String name, int calories, int protein){
        this.id = id; this.dateEntered = dateEntered; this.diningId = diningId; this.name = name;
        this.calories = calories; this.protein = protein; isSelected = false;
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
        protein = 0;
    }

    public int getProtein() {
        return protein;
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

    public boolean isHeader(){
        return false;
    }
}
