package com.example.app;

import java.io.Serializable;

/**
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 `Name` varchar(30) NOT NULL,
 `MealType` enum('Breakfast','Lunch','Dinner','Dessert','Snack/Appetizer') NOT NULL,
 `Ingredients` text NOT NULL,
 `IngredientsWithNumVal` text NOT NULL,
 `TimeDisplay` enum('<5min','5-10min','10-20min','20-30min','30+min') NOT NULL,
 `TimeActual` int(11) NOT NULL,
 `InstructionsText` text NOT NULL,
 `Calories` int(11) NOT NULL,
 `Rating` int(11) NOT NULL,
 * Created by ehaydenr on 3/17/14.
 */
public class Recipe implements Serializable, Comparable<Recipe>{
    private long id;
    private long TimeActual;

    private long Calories;
    private long Rating;
    private String Name, MealType, Ingredients, IngredientsWithNumVal, TimeDisplay, InstructionsText;
    private int score;

    @Override
    public int compareTo(Recipe recipe) {
        if (this.score > recipe.score) return 1;
        if (this.score < recipe.score) return -1;
        else return 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeActual() {
        return TimeActual;
    }

    public void setTimeActual(long timeActual) {
        TimeActual = timeActual;
    }

    public long getCalories() {
        return Calories;
    }

    public void setCalories(long calories) {
        Calories = calories;
    }

    public long getRating() {
        return Rating;
    }

    public void setRating(long rating) {
        Rating = rating;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMealType() {
        return MealType;
    }

    public void setMealType(String mealType) {
        MealType = mealType;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getIngredientsWithNumVal() {
        return IngredientsWithNumVal;
    }

    public void setIngredientsWithNumVal(String ingredientsWithNumVal) {
        IngredientsWithNumVal = ingredientsWithNumVal;
    }

    public String getTimeDisplay() {
        return TimeDisplay;
    }

    public void setTimeDisplay(String timeDisplay) {
        TimeDisplay = timeDisplay;
    }

    public String getInstructionsText() {
        return InstructionsText;
    }

    public void setInstructionsText(String instructionsText) {
        InstructionsText = instructionsText;
    }

    public String toString(){
        return this.getName();
    }
}
